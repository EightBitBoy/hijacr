package de.eightbitboy.hijacr.events;

import de.eightbitboy.hijacr.data.dao.Comic;

/**
 * For notifying subscribers that a comic has been selected from the comics list.
 */
public class ComicSelectedEvent {
	public final Comic comic;

	public ComicSelectedEvent(Comic comic) {
		this.comic = comic;
	}
}
