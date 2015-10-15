package de.eightbitboy.hijacr.events;

import de.eightbitboy.hijacr.data.comic.ComicData;

/**
 * For notifying subscribers that a comic has been selected from the comics list.
 */
public class ComicSelectedEvent {
	public final ComicData comicData;

	public ComicSelectedEvent(ComicData comicData) {
		this.comicData = comicData;
	}

	@Override
	public String toString() {
		return comicData.toString();
	}
}
