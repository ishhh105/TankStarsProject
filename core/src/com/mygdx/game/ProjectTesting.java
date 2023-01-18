package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.*;
import com.mygdx.game.screens.PlayScreen;

import java.io.Serializable;

public class ProjectTesting extends Game implements Serializable {
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;

	public SpriteBatch batch;
	public Texture img;
	public Sprite sprite;
	public OrthographicCamera camera;
	public BitmapFont font;

	public AssetManager assets;
	public LoadingScreen loadingScreen;
	public SelectScreen selectScreen;

	public PlayScreen playScreen;

	public static boolean isLoad = false;


	@Override
	public void create () {
		assets = new AssetManager();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		font = new BitmapFont();

		selectScreen = new SelectScreen(this);
		loadingScreen = new LoadingScreen(this);
		playScreen = new PlayScreen(this);



		this.setScreen(new SelectScreen(this));


	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
		font.dispose();
		//img.dispose();
		this.getScreen().dispose();
	}
}
