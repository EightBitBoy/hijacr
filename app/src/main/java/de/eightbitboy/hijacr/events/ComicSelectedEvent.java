package de.eightbitboy.hijacr.events;

import de.eightbitboy.hijacr.data.comic.ComicData;

public class ComicSelectedEvent {
	public final ComicData comicData;

	public ComicSelectedEvent(ComicData comicData) {
		this.comicData = comicData;
	}
}
