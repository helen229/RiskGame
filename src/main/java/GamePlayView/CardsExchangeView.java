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
        String head = "*************** CardsView *******************";
        ArrayList<Card> cardList= ((GameModel)obs).getCurrentPlayer().getCardList();
        System.out.println(head);
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            System.out.println("Card "+i+" "+card.getCardTypeName());
        }
        System.out.println(head);


    };

}
