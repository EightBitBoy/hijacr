package de.eightbitboy.hijacr.events;

import de.eightbitboy.hijacr.data.comic.SimpleComicData;

/**
 * For notifying subscribers that a comic has been selected from the comics list.
 */
public class ComicSelectedEvent {
	public final SimpleComicData comicData;

	public ComicSelectedEvent(SimpleComicData comicData) {
		this.comicData = comicData;
	}
}
