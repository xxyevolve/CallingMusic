package com.xxyevolve.callingmusic.dao;

import java.util.List;


public interface PlaylistDao {
	void createPlaylist(String name);

	void removePlaylist(String id);

	List<Playlist> getAllPlaylist();

}
