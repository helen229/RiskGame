package GamePlayView;

import GamePlayModel.Card;
import GamePlayModel.CardType;
import GamePlayModel.GameModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class has one method that updates the  card exchange view automatically
 */

public class CardsExchangeView implements Observer {
    public void update(Observable obs, Object arg) {
        if (!"CardsView".equals(arg))
            return;
        String player = ((GameModel)obs).getCurrentPlayer().getPlayerName();
        int currentExchangeTry = ((GameModel)obs).getCurrentExchangeTry();
        ArrayList<Card> cardList= ((GameModel)obs).getCurrentPlayer().getCardList();
        System.out.println("\n*************** Card Exchange View *****************");
        System.out.println("Current Player is: "+player);
        System.out.println("Currently, each card can reinforce "+ (currentExchangeTry*5)+" number of armies.");
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            System.out.println("Card "+i+" = "+card.getCardTypeName());
        }
        System.out.println("*************** Card Exchange View *****************\n");
    }
}