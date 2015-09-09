package de.eightbitboy.hijacr.events;

import de.eightbitboy.hijacr.data.comic.SimpleComicData;

public class ComicSelectedEvent {
	public final SimpleComicData comicData;

	public ComicSelectedEvent(SimpleComicData comicData) {
		this.comicData = comicData;
	}
}
