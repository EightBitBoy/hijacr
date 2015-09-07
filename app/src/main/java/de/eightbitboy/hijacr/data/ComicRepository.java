package de.eightbitboy.hijacr.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComicRepository {
    private Map<String, ComicData> comics = new HashMap<String,ComicData>();

    public ComicRepository(){
        initializeComics();
    }

    private void initializeComics(){
        comics.put("ExtraLife", new ComicData("ExtraLife", "div[id=comic] img[src]", null, null));
    }

    public ComicData getComicData(String key){
        return comics.get(key);
    }
}
