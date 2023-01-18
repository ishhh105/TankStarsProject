package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.ProjectTesting;



public class LoadingScreen implements Screen {

    private final ProjectTesting app;

    private SelectScreen app1;
    private Stage stage;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    //private

    private Image MainImage;
    private Image TitleImage;
    private TextButton buttonPlay, buttonExit, buttonLoad;
    private Skin skin;

    Sound sound;






    public LoadingScreen(final ProjectTesting app){
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.stage = new Stage(new FillViewport(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT, app.camera));

        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("skin/level-plane-ui.atlas")));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("skin/level-plane-ui.json"));


        Texture mainMenu = new Texture(Gdx.files.internal("FinalBG.jpg"));
        MainImage = new Image(mainMenu);
        MainImage.setPosition(0, 0);
        MainImage.setSize(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT);
        stage.addActor(MainImage);

        Texture title = new Texture(Gdx.files.internal("Title-removebg-preview.jpg"));
        TitleImage = new Image(title);
        TitleImage.setPosition(375, 300);
        TitleImage.setSize(500, 500);
        stage.addActor(TitleImage);



    }
    @Override
    public void show() {
        //sound = Gdx.audio.newSound(Gdx.files.internal("Fortnite _ AHOY! Lobby Music (Season 8 Music Pack).mp3"));
        //sound.play();



        System.out.println("MAINMENU");
        initButtons();
    }



    @Override
    public void render(float delta) {


        //update(delta);


        stage.draw();
        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        stage.dispose();


    }

    private void initButtons(){
        buttonPlay = new TextButton("Play with Friends", skin, "default");
        buttonPlay.setPosition(525, 300);
        buttonPlay.setSize(200, 80);
        stage.addActor(buttonPlay);


        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.playScreen);
            }
        });


        buttonLoad = new TextButton("Load Game", skin, "default");
        buttonLoad.setPosition(525, 200);
        buttonLoad.setSize(200, 80);
        buttonLoad.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTesting.isLoad = true;
                app.setScreen(new GamePlayScreen(app));
            }
        });
        stage.addActor(buttonLoad);

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(525, 100);
        buttonExit.setSize(200, 80);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        stage.addActor(buttonExit);



    }

}
