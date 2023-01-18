package com.mygdx.game.screens;

//import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.ProjectTesting;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;



public class SelectScreen implements Screen {

    private static SelectScreen instance;
    private final ProjectTesting app;

    private float progress;
    private Stage stage;
    private Image TankImage;
    private OrthographicCamera camera;


    public SelectScreen(final ProjectTesting app){
        this.app = app;
        camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT, app.camera));
        Gdx.input.setInputProcessor(stage);

        Texture splashTexture = new Texture(Gdx.files.internal("FinalLoadingBackground.jpg"));
        TankImage = new Image(splashTexture);
        TankImage.setPosition(0 , 0 );
        TankImage.setSize(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT);

        stage.addActor(TankImage);
    }
    //Singleton Design Pattern
    public static SelectScreen getInstance(ProjectTesting app) {
        if (instance == null) {
            instance = new SelectScreen(app);
        }
        return instance;
    }
    @Override
    public void show() {
        System.out.println("LOAD SCREEN");
    }

    @Override
    public void render(float delta) {

        update(delta);


        stage.draw();
        Gdx.input.setInputProcessor(stage);


        app.batch.begin();
        //app.font.draw(app.batch, "Select Screen!",20,20);
        if(Gdx.graphics.getFramesPerSecond()> 0){
            app.setScreen(new LoadingScreen(app));
        }
        app.batch.end();


    }

    public void update(float delta){
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }

    @Override
    public void resume() {
        System.out.println("Resume");
    }

    @Override
    public void hide() {
        System.out.println("Hide");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
        stage.dispose();
    }
}

