package GamePlayView;

import GamePlayModel.Card;
import GamePlayModel.CardType;
import GamePlayModel.GameModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CardsExchangeView implements Observer {
    public void update(Observable obs, Object arg) {
        if (!"CardsView".equals(arg))
            return;
        ArrayList<Card> cardList= ((GameModel)obs).getCurrentPlayer().getCardList();
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            System.out.println("Card "+i+" "+card.getCardTypeName());
        }

    };

}
