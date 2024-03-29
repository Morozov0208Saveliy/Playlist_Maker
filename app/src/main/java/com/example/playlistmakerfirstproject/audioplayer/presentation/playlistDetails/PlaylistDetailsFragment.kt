package com.example.playlistmakerfirstproject.audioplayer.presentation.playlistDetails

import android.content.DialogInterface
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.playlistmakerfirstproject.R
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Playlist
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.presentation.search.SearchViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.search.TrackAdapter
import com.example.playlistmakerfirstproject.databinding.FragmentPlaylistDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.Locale
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailsFragment : Fragment(), TrackAdapter.OnItemClickListener,
    TrackAdapter.OnItemLongClickListener {

    private val viewModel: PlaylistDetailsFragmentViewModel by activityViewModel()
    private val searchTrackViewModel: SearchViewModel by viewModel()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private var playlist: Playlist? = null
    private var updatedPlaylist: Playlist? = null
    private var playlistToEdit: Playlist? = null

    private var _binding: FragmentPlaylistDetailsBinding? = null
    private val binding get() = _binding!!

    private var adapter = TrackAdapter(
        arrayListOf(),
        this@PlaylistDetailsFragment,
        this@PlaylistDetailsFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)




        playlist = arguments?.getSerializable("playlist") as? Playlist

        if (updatedPlaylist == null || playlist?.id != updatedPlaylist?.id) {

            viewModel.getPlaylistById(playlist!!.id)
            viewModel.getTracksFromOnePlaylist(playlist!!)
            updatedPlaylist = playlist!!.copy()
            playlistToEdit = playlist!!.copy()

        } else {

            viewModel.getPlaylistById(updatedPlaylist!!.id)
            viewModel.getTracksFromOnePlaylist(updatedPlaylist!!)
            playlistToEdit = updatedPlaylist!!.copy()

        }

        val bottomSheetContainer = binding.standardBottomSheetMenuDetails
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        setUpClickListeners()
        setupBottomSheetBehaviorCallback()
    }


    private fun setupUI() {
        binding.recycleViewBottomSheet.layoutManager = LinearLayoutManager(requireContext())

    }


    private fun setupObservers() {
        viewModel.playlistDetails.observe(viewLifecycleOwner) { playlist ->
            updateUi(playlist)
            playlistToEdit!!.name = playlist.name
            playlistToEdit!!.details = playlist.details
            playlistToEdit!!.imagePath = playlist.imagePath


        }
        viewModel.tracksLiveData.observe(viewLifecycleOwner) { tracks ->
            val trackIds = tracks?.map { it.trackId }
            val totalTrackTimeMillis = tracks?.sumBy { it.trackTimeMillis }
            updatedPlaylist = updatedPlaylist!!.copy(idOfTracks = trackIds)
            showContent(tracks, totalTrackTimeMillis)
            playlistToEdit!!.idOfTracks = trackIds
            playlistToEdit!!.numberOfTracks = tracks?.size

        }

    }

    private fun showContent(tracks: List<Track>?, duration: Int?) {

        if (tracks.isNullOrEmpty()) {
            binding.apply {

                messageEmptyList.visibility = View.VISIBLE
                recycleViewBottomSheet.visibility = View.GONE
                playlistMinutes.text = "0 минут"
                playlistTracks.text = "0 треков"
            }

        }
        if (!tracks.isNullOrEmpty()) {
            binding.messageEmptyList.visibility = View.GONE
            binding.recycleViewBottomSheet.visibility = View.VISIBLE
            val numberOfTracks = tracks.size
            val updatedTracks = tracks.map { track ->
                track.copy(artworkUrl100 = track.artworkUrl100.replaceAfterLast('/', "60x60bb.jpg"))
            }


            with(binding) {
                val formattedDuration = getFormattedDuration(duration)
                adapter.tracks = ArrayList(updatedTracks)
                recycleViewBottomSheet.adapter = adapter
                adapter.notifyDataSetChanged()
                recycleViewBottomSheet.visibility = View.VISIBLE
                playlistMinutes.text =
                    "$formattedDuration ${getMinuteWordForm(formattedDuration.toInt() ?: 0)}"
                playlistTracks.text =
                    "${numberOfTracks.toString()} ${getTrackWordForm(numberOfTracks)}"
            }
        }
    }

    private fun setUpClickListeners() {
        binding.icBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.iconShare.setOnClickListener {
            if (updatedPlaylist?.numberOfTracks == 0) {
                Toast.makeText(
                    requireContext(),
                    context?.getString(R.string.no_tracks_to_share),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.shareTracks(playlist!!, adapter.tracks)
            }
        }

        binding.iconMenu.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.dimOverlay.visibility = View.VISIBLE
        }

        binding.sharePlaylist.setOnClickListener {
            if (updatedPlaylist?.numberOfTracks == 0) {
                Toast.makeText(
                    requireContext(),
                    context?.getString(R.string.no_tracks_to_share),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.shareTracks(playlist!!, adapter.tracks)
            }
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        }

        binding.deletePlaylist.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            MaterialAlertDialogBuilder(requireContext(), R.style.DialogStyle)
                .setTitle(context?.getString(R.string.dialog_delete_playlist_title))
                .setMessage(context?.getString(R.string.dialog_delete_playlist_message))
                .setNeutralButton(context?.getString(R.string.dialog_delete_playlist_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.dialog_delete_playlist_delete)) { _, _ ->
                    lifecycleScope.launch {
                        viewModel.deletePlaylistById(playlist!!.id, adapter.tracks)
                        findNavController().popBackStack()
                    }
                }
                .setOnDismissListener {
                }
                .show()
                .apply {
                    getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#3772E7"))
                    getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#3772E7"))
                }


        }

        binding.editPlaylist.setOnClickListener {
            viewModel.getPlaylistById(updatedPlaylist!!.id)
            val playlist: Playlist? = playlistToEdit
            val bundle = Bundle().apply {
                putSerializable("EDIT_PLAYLIST", playlist)
            }
            findNavController().navigate(R.id.playlistFragment, bundle)
        }
    }

    private fun setupBottomSheetBehaviorCallback() {
        val dimOverlay: View = binding.dimOverlay

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        dimOverlay.alpha = 1f
                        dimOverlay.visibility = View.VISIBLE
                    }

                    else -> {
                        dimOverlay.visibility = View.GONE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                dimOverlay.alpha = slideOffset
            }
        })
    }

    private fun setupBottomSheet(playlist: Playlist) {
        binding.recycleViewBottomSheet.layoutManager = LinearLayoutManager(requireContext())
        binding.playlistNameBtnSheet.text = playlist.name
        binding.numberOfTracksBtnSheet.text =
            "${playlist.numberOfTracks.toString()} ${getTrackWordForm(playlist.numberOfTracks ?: 0)}"
        Glide.with(requireContext())
            .load(playlist.imagePath)
            .placeholder(R.drawable.placeholder)
            .into(binding.playlistCoverImageBtnSheet)
    }


    private fun updateUi(playlist: Playlist) {

        setupBottomSheet(playlist)
        with(binding) {
            playlistName.text = playlist.name
            playlistDetails.text = playlist.details
            playlistTracks.text =
                "${playlist.numberOfTracks.toString()} ${getTrackWordForm(playlist.numberOfTracks ?: 0)}"
            Glide.with(root.context)
                .load(playlist.imagePath)
                .placeholder(R.drawable.placeholder_big)
                .into(imagePlaylistCover)
        }

        if (playlist.details?.isEmpty() == true) {
            binding.playlistDetails.visibility = View.GONE
        } else {
            binding.playlistDetails.visibility = View.VISIBLE
        }

    }


    override fun onItemClick(track: Track) {
        searchTrackViewModel.openTrackAudioPlayer(track)

    }

    override fun onItemLongClick(track: Track): Boolean {
        MaterialAlertDialogBuilder(requireContext(), R.style.DialogStyle)
            .setTitle(context?.getString(R.string.dialog_delete_title))
            .setMessage(context?.getString(R.string.dialog_delete_message))
            .setNeutralButton(context?.getString(R.string.dialog_delete_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.dialog_delete_delete)) { _, _ ->
                lifecycleScope.launch {
                    viewModel.deleteTrackFromPlaylist(track, updatedPlaylist!!)
                }
            }
            .setOnDismissListener {
            }
            .show()
            .apply {
                getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#3772E7"))
                getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#3772E7"))
            }


        return true
    }


    private fun getTrackWordForm(count: Int): String {
        return when {
            count % 100 in 11..14 -> "треков"
            count % 10 == 1 -> "трек"
            count % 10 in 2..4 -> "трека"
            else -> "треков"
        }
    }

    private fun getMinuteWordForm(count: Int): String {
        return when {
            count % 100 in 11..14 -> "минут"
            count % 10 == 1 -> "минута"
            count % 10 in 2..4 -> "минуты"
            else -> "минут"
        }
    }

    private fun getFormattedDuration(duration: Int?): Int {
        if (duration !== null) {
            return SimpleDateFormat("mm", Locale.getDefault()).format(duration).toInt()
        } else {
            return 0
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}