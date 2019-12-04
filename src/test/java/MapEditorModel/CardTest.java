/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorModel;

import GamePlayModel.Card;
import GamePlayModel.CardType;
import GamePlayModel.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Ehsan
 */
public class CardTest {
private CardType cardType;
private PlayerModel player;
public Card card;

/**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {
        card = new Card(player);
    }
    
    @Test
    public void testCard() {
        
        assertTrue(card.getCardType().equals("Infantry") || card.getCardType().equals("Cavalry") || card.getCardType().equals("Artillery"));
    }  
}
