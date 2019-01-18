
//Pakke navn
package sample;

//Importere fra klassen. Blandt andet en extern klasse. Så kan man bruge disse metoder
//En klasse som hedder FXGL, der er en spil klasse som er som JAVAFX
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;


//En publik klasse som nedarver fra GameApplication(FXGL klasse)
public class PongApp extends GameApplication {

    //En privat(kun tilgængelig i denne klasse) statisk final(har et fast værdi) integer
    //med en erklæring og deklarering af padle bredde, som angives til 30
    private static final int PADDLE_WIDTH = 30;
    //En privat(kun tilgængelig i denne klasse) statisk final(har et fast værdi) integer
    //med en erklæring og deklarering af padle højde, som angives til 100
    private static final int PADDLE_HEIGHT = 100;
    //En privat(kun tilgængelig i denne klasse) statisk final(har et fast værdi) integer
    //med en erklæring og deklarering af bold størrelse, som angives til 30
    private static final int BALL_SIZE = 30;
    //En privat(kun tilgængelig i denne klasse) statisk final(har et fast værdi) integer
    //med en erklæring og deklarering af palde hastigheden(hastigheden til at flytte), som angives til 5
    private static final int PADDLE_SPEED = 5;
    //En privat(kun tilgængelig i denne klasse) statisk final(har et fast værdi) integer
    //med en erklæring og deklarering af bold hastigheden(hastigheden til at flytte), som angives til 4
    private static final int BALL_SPEED = 4;

    //Privat objekt af klassen Entity, hvor objektet hedder paddle1
    private Entity paddle1;
    //Privat objekt af klassen Entity, hvor objektet hedder paddle2
    private Entity paddle2;
    //Privat objekt af klassen Entity, hvor objektet hedder ball
    private Entity ball;

    //For at override en methode i superklassen
    @Override
    //En protected(tilgængelig i samme subklasse med samme pakke)
    //En methode fra superklassen som erkærer spillets indstillinger. Den har en parametre med en
    //Objekt af klassen Gamesttings som hedder settings.
    protected void initSettings(GameSettings settings) {
        //En metode af en klasse med en string parameter...
        settings.setTitle("Pong");
    }

    //For at override en methode i superklassen
    @Override
    //En protected(Tilgængelig i samme subklasse med samme pakke)
    //En metode som hedder initInput i superklassen. Denne metode er til inputs(tastatur indstillinger) for spillet.
    protected void initInput()
    {
        //en objekt med en metode, med en parameter med en ny objekt, som har en string parameter
        getInput().addAction(new UserAction("Up 1")
        {
            //For at override en metode i superklassen.
            @Override
            //En protected(Tilgængelig i samme subklasse med samme pakke)
            //En metode som hedder onAction i superklassen. Denne metode er når der sker en handling, når
            //man trykker en frem i tastaturet.
            protected void onAction() {
                //En objekt med en metode, der har en parameter
                //Objektet går fremad når der sker en handling, når man trykker på tastaturet W
                paddle1.translateY(-PADDLE_SPEED);
            }
            //Disse handlinger gives til tastaturet W
        }, KeyCode.W);
        //En objekt med en metode, med en parameter med en ny objekt, som har en string parameter
        getInput().addAction(new UserAction("Down 1") {
            //For at override en metode i superklassen
            @Override
            //En protected metode fra superklassen, som hedder onAction
            //En metode uden en parameter
            protected void onAction() {
                //Objektet med en metode, der har en parameter
                //Denne sætter padle 1 til at går ned,
                paddle1.translateY(PADDLE_SPEED);
            }
            //Disse handlinger gives til tastaturet S
        }, KeyCode.S);

        //En objekt med en metode, hvor der en parameter med en ny objekt der har en string parameter
        getInput().addAction(new UserAction("Up 2") {
            //Denne overrider metoden i superklassen
            @Override
            //En protected metode i superklassen, uden parameter
            protected void onAction() {
                //Objektet padle 2 har en metode med en parameter
                paddle2.translateY(-PADDLE_SPEED);
            }
            //Disse handlinger tildeles til tastaturet 'UP'
        }, KeyCode.UP);

        //En objekt med en metode, hvor der er en parameter med en ny objekt der har en string parameter.
        getInput().addAction(new UserAction("Down 2") {
            //For at override metoden i superklassen
            @Override
            //En protected metode uden en parameter
            protected void onAction() {
                //Objektet har en matode med en parameter
                paddle2.translateY(PADDLE_SPEED);
            }
            //Disse handlinger tildeles til tastaturet 'DOWN'
        }, KeyCode.DOWN);
    }

    //For at override/Overskrive en metode i superklassen
    @Override
    //En protected metode med en parameter
    //En metode der har en parameter, af typen Map, som er en string og en objekt.
    protected void initGameVars(Map<String, Object> vars) {
        //En objekt med en metode med to parametre, den ene er string mens den anden er integer
        vars.put("score1", 0);
        //En objekt med en metode med to parametre, den ene er string mens den anden er integer
        vars.put("score2", 0);
    }

    //For at override/Overskrive en metode i superklassen.
    @Override
    //En protected metode uden en parameter
    protected void initGame() {
        //Objektet bliver skabt i x og y aksen
        paddle1 = spawnBat(0, getHeight() / 2 - PADDLE_HEIGHT / 2);
        //Objektet bliver skabt i x og y aksen
        paddle2 = spawnBat(getWidth() - PADDLE_WIDTH, getHeight() / 2 - PADDLE_HEIGHT / 2);
        //Objektet bliver skabt i x og y aksen
        ball = spawnBall(getWidth() / 2 - BALL_SIZE / 2, getHeight() / 2 - BALL_SIZE / 2);
    }

    //For at override/Overskrive en metode i superklassen
    @Override
    //En proteced metode som initialiserer UIen
    protected void initUI() {
        //En objekt af typen Text som både erklæres og deklareres.
        Text textScore1 = getUIFactory().newText("", Color.BLUE, 22);
        Text textScore2 = getUIFactory().newText("", Color.BLACK, 22);

        //En objekt med en metode med en parameter
        textScore1.setTranslateX(10);
        //En objekt med en metode med en parameter
        textScore1.setTranslateY(50);

        //Et objekt med en metode med en parameter
        textScore2.setTranslateX(getWidth() - 30);
        //Et objekt med en metode med en parameter
        textScore2.setTranslateY(50);

        //Objekt med flere metoder med flere parameter
        textScore1.textProperty().bind(getGameState().intProperty("score1").asString());
        //Objekt med flere metoder med flere parameter
        textScore2.textProperty().bind(getGameState().intProperty("score2").asString());
        //Metode som har en metode med flere parameter
        getGameScene().addUINodes(textScore1, textScore2);
    }

    //For at override/Overskrive en metode i superklassen.
    @Override
    //En protected metode onUpdate fra superklassen med en parameter af typen double, som er kommatal
    protected void onUpdate(double tpf) {
        //En ny objekt af klassen Point2D, der eklæres og deklæreres med et objekts metode, der har en parameter
        Point2D velocity = ball.getObject("velocity");
        //Objektet med en metode, der har en parameter
        ball.translate(velocity);

        //If løkke, som har et objekts metode, hvor den skal være ligemed et andet objekts metode
        //Og et objekts metode er mindre end et andet objekts metode
        //Og et objekts metode er større en et andet objekts metode
        //Så gør objektets metode med flere parameter, hvor der er et string og en nyt objekt med et objekts metode og
        //Et andet objekts metode
        if (ball.getX() == paddle1.getRightX()
                && ball.getY() < paddle1.getBottomY()
                && ball.getBottomY() > paddle1.getY()) {
            ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
        }
        //If løkke, som har et objekts metode, hvor den skal være ligemed et andet objekts metode
        //Og et objekts metode er mindre end et andet objekts metode
        //Og et objekts metode er større en et andet objekts metode
        //Så gør objektets metode med flere parameter, hvor der er et string og en nyt objekt med et objekts metode og
        //Et andet objekts metode

        if (ball.getRightX() == paddle2.getX()
                && ball.getY() < paddle2.getBottomY()
                && ball.getBottomY() > paddle2.getY()) {
            ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
        }

        //Et if løkke, som har et parameter, hvor et objekts metode er mindre eller lig med 0
        if (ball.getX() <= 0)
        {
            //Et objekts metoede som har to parameter, hvor den ene er string og den anden er addition til et integer
            getGameState().increment("score2", +1);
            //Et metode, der genstarter et objekt
            resetBall();
        }

        //Et if lølle, som har et parameter, hvor et objekts metode er større eller lig med et metode
        if (ball.getRightX() >= getWidth()) {
            //Et objekt med en metode der har flere parameter, som er en string og et addition til et integer
            getGameState().increment("score1", +1);
            //En metode, som genstarter et objekt.
            resetBall();
        }

        //Et if løkke, med et parameter, hvor objektet metode er mindre end eller lig med 0
        if (ball.getY() <= 0) {
            //Et objekts metode, hvor der er en parameter
            ball.setY(0);
            //Et objekt med en metode, med flere parametre, hvor der er en string og et nyt objekt, og en objekt med en metode
            ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
        }

        //Et if løkke, som har et parameter, som er et objekts metode skal være større eller lig meget et metode
        if (ball.getBottomY() >= getHeight()) {
            //Et objekt med et metode, som har et parameter der er et metode subtraktion af et variabel
            ball.setY(getHeight() - BALL_SIZE);
            //Et objekts metode som har flere parameter, hvor der er en string og et nyt objekt med et objekts metode
            //Subtraktion af en objekts metode
            ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
        }
    }

    //En privat(Kan kun tilgås fra denne klasse) objekt af klassen som har to parameter, som er kommatal, og x y
    //Denne minder en del af ToString metoden, hvor ToString metoden laver objektet om til en string
    //Denne metode bygger en objekt med hjælp, fra builder metoden fra Entities klassen
    private Entity spawnBat(double x, double y) {
        //Returner Metoden fra klassen
        return Entities.builder()
                //En metode med to parametre
                .at(x, y)
                //En metode med en objekt parameter, som igen har to parametre
                .viewFromNodeWithBBox(new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT))
                //En metode som bygger objektet
                .buildAndAttach();
    }
    //En privat(Kan kun tilgås fra denne klasse) objekt af klassen som har to parameter, som er kommatal, og x y
    //Denne minder en del af ToString metoden, hvor ToString metoden laver objektet om til en string
    //Denne metode bygger en objekt med hjælp, fra builder metoden fra Entities klassen
    private Entity spawnBall(double x, double y) {
        //Returner metoden klassen
        return Entities.builder()
                //En metode med to parametre
                .at(x, y)
                //En metode med en objekt parameter, som igen har to parametre
                .viewFromNodeWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE))
                //En metode med en string parameter, og et nyt objekt med to parametre
                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                //En metode som bygger objektet
                .buildAndAttach();
    }
    //En privat(Kan kun tilgås fra denne klasse) metode uden parameter
    private void resetBall() {
        //Objektet har en metode to parametre, som sætter
        ball.setPosition(getWidth() / 2 - BALL_SIZE / 2, getHeight() / 2 - BALL_SIZE / 2);
        ball.setProperty("velocity", new Point2D(BALL_SPEED, BALL_SPEED));
    }

    public static void main(String[] args) {
        launch(args);
    }
}