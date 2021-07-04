package com.example.shopr.domainenums;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;

@Getter
public enum GameGenre {
    MMORPG, RPG, FPS, RTS, RACE;
}
