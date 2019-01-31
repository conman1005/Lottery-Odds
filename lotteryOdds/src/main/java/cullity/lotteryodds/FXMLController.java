package cullity.lotteryodds;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    Label lblTickets;

    @FXML
    Label lblMoney;
    @FXML
    Label lblMoneyPP;

    @FXML
    Label lblTime;
    @FXML
    Label lblYears;
    
    @FXML
    Label lblTitle;

    @FXML
    Button btnStart;

    @FXML
    CheckBox chkSlowCount;

    int money = 0;
    int time = 0;
    int tickets = 0;

    int clock = 0;

    Timeline count = new Timeline(new KeyFrame(Duration.millis(1), ae -> ticketBought()));
    
    DecimalFormat dec = new DecimalFormat("0.00");

    //odds of winning the Powerball jackpot is 1 in 292201338
    private void ticketBought() {
        tickets++;
        money += 2;
        if (!chkSlowCount.isSelected()) {
            lblTickets.setText("Tickets Bought: " + tickets);
            lblMoney.setText("Money Spent: " + tickets * 2);
            lblMoneyPP.setText("Money Spent (Power Play): " + tickets * 3);
        }

        clock++;
        if (clock == 1000) {
            time++;
            clock = 0;
            lblTime.setText("Seconds Past: " + time);
            lblTickets.setText("Tickets Bought: " + tickets);
            lblMoney.setText("Money Spent: " + money);
            lblMoneyPP.setText("Money Spent (Power Play): " + tickets * 3);
            lblYears.setText("Years Past: " + dec.format(tickets / 52));
        }
        
        if (ThreadLocalRandom.current().nextInt(0, 292201338) == 1) {
            count.stop();
            lblTickets.setText("Tickets Bought: " + tickets);
            lblMoney.setText("Money Spent: " + tickets * 2);
            lblMoneyPP.setText("Money Spent (Power Play): " + tickets * 3);
            lblTitle.setText("Bought " + tickets + " tickets to win");
        }
    }
    
    @FXML
    private void btnStart() {
        count.setCycleCount(Timeline.INDEFINITE);
        count.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
