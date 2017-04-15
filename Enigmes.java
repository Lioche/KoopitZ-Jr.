package be.lioche.skype.bot;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.events.EventHandler;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.events.chat.message.MessageReceivedEvent;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.user.User;

public class Events implements Listener{
	
	@EventHandler
	public void onMessageReceived(MessageReceivedEvent e) throws ConnectionException, ChatNotFoundException, IOException{
		
		String msg = e.getMessage().getContent().asPlaintext();
		User sender = e.getMessage().getSender();
		Chat conv = e.getMessage().getChat();
		conv.startTyping();
		if(!sender.getUsername().equalsIgnoreCase("liochelamyche")){
			SBot.chatToMe("("+e.getMessage().getSender().getDisplayName()+") Level "+SBot.LEVEL + ":  “"+msg+"”", false, false);
		}else{
			if(msg.startsWith("!nono")){
				SBot.chat(SBot.sk.getOrLoadContact("live:noemie.onbelet").getPrivateConversation(), msg.substring(6, msg.length()), false, false, false);
			}else if(msg.equalsIgnoreCase("!startthegame")){
				SBot.CANPLAY = true;
			}
		}
		/* --------------- SECRETS / EASTER EGGS --------------- */
		
		switch (msg.toLowerCase()) {
		case "♥":
		case "<3":
		case "jtm":
		case "je t'aime":
		case "i love you":
			if(Secrets.LOVE.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chatColor(conv, Secrets.LOVE.get(), SBot.RED);
				Secrets.LOVE.setFound();
			}
			break;
		case "by":
			if(Secrets.BY.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.BY.get(), false, false, false);
				Secrets.BY.setFound();
			}
			
			break;
		case "anniversaire":
			SBot.chat(conv, Secrets.ANNIVERSAIRE.get(), false, false, false);
			break;
//			
		case "jsp":
		case "je ne sais pas":
		case "je sais pas":
			if(Secrets.JE_SAIS_PAS.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.JE_SAIS_PAS.get(), false, false, false);
				Secrets.JE_SAIS_PAS.setFound();
			}
			break;
		case "secret":
			if(Secrets.SECRET.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.SECRET.get(), false, false, false);
				Secrets.SECRET.setFound();
			}
			break;
//			
		case "humain":
		case "poulet":
			if(Secrets.POULET.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.POULET.get(), false, false, false);
				Secrets.POULET.setFound();
			}
			break;
		case "bb lou":
		case "bb loup":
			if(Secrets.BB_LOU.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.BB_LOU.get(), false, false, false);
				Secrets.BB_LOU.setFound();
			}
			break;
//			
		case "fox":
		case "renard":
			if(Secrets.RENARD.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.RENARD.get(), false, false, false);
				Secrets.RENARD.setFound();
			}
			break;
		case "juniper":
			if(Secrets.JUNIPER.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.JUNIPER.get(), false, false, false);
				SBot.chat(conv, Secrets.TF_INDICE.get(), false, true, false);
				Secrets.JUNIPER.setFound();
			}
			break;
		case "juni bébé renard":
			if(Secrets.JUNI_BB.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.JUNI_BB.get(), false, false, false);
				Secrets.JUNI_BB.setFound();
			}
			break;
//			
		case "the finder":
			if(Secrets.THE_FINDER.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.THE_FINDER.get(), false, false, false);
				Secrets.THE_FINDER.setFound();
			}
			break;
		case "beauticu":
			if(Secrets.BEAUTICU.isFound()){
				SBot.chatColor(conv, SBot.SECRET, SBot.RED);
			}else{
				SBot.chat(conv, Secrets.BEAUTICU.get(), false, false, false);
				Secrets.BEAUTICU.setFound();
			}
			break;
		default:
			if(SBot.FINISH || SBot.WAIT || sender.getUsername().equalsIgnoreCase("liochelamyche")) break; /* Si la partie est terminée ou si pas encore temps on ne lance pas */
			if(!SBot.CANPLAY){
				Calendar cal = Calendar.getInstance();
				cal.set(2017, 03, 15, 10, 15, 00);
				Date target = cal.getTime();
				Date now = new Date();
				
				long diff = target.getTime()-now.getTime();
				int m = (int) TimeUnit.MILLISECONDS.toMinutes(diff);
				int s = (int) TimeUnit.MILLISECONDS.toSeconds(diff);
				
				if(m <= 1 && s > 0){
					SBot.chatColor(conv, "✗ - Patiente encore "+s+" secondes.. (presque!) :33", SBot.RED);
					break;
				}else if(m > 1){
					SBot.chatColor(conv, "✗ - Patiente encore "+m+" minutes.. (mariachilove)", SBot.RED);
					break;
				}else{
//					SBot.CANPLAY = true;
				}
			}
			switch (SBot.LEVEL) {
			case 0:
				/* --------------- INTRO ET DEBUT DE LA PARTIE --------------- */
				SBot.START = new Date();
				SBot.LEVEL++;
				SBot.jf.showText("Début de la partie");
				SBot.chatToMe("Début de la partie le {date} à {time} !", true, false);
				SBot.chatMultiple(conv, 
						"Hey. Je me prénomme KoopitZ !",
						"Comme je sais que c'est ton anniversaire, je te propose un petit jeu.",
						"- Des énigmes vont t'être données grâce auxquelles tu dois trouver un lieu.",
						"- A chaque endroit t'attendra un papier avec une lettre.",
						"- Envoie moi la lettre correcte pour passer à l'énigme suivante.",
						"- Pour chaque énigme tu as droit à un indice. Envoie 'indice'",
						"- Rassemble les lettres pour former un mot et trouver un dernier lieu.");
				SBot.chat(conv, "La partie commence maintenant ! Ne triche pas ! Bonne chance :3", true, false, false);
				SBot.chat(conv, "———————————", false, false, false);
				SBot.chat(conv, SBot.LEVEL+": "+SBot.ENIGMES[SBot.LEVEL-1], false, true, false);
				break;
			case 4:
				/* --------------- QUESTION SPECIALE NOMBRE DE JOURS --------------- */
				if(msg.equalsIgnoreCase("indice")){
					if(!SBot.TIP){
						SBot.chatColor(conv, "▸ "+SBot.INDICES[SBot.LEVEL-1], SBot.BLUE);
						SBot.TIP = true;
					}else{
						SBot.chatColor(conv, SBot.ALREADY, SBot.RED);
					}
				}else if(msg.equalsIgnoreCase(SBot.NB)){
					SBot.LEVEL++;
					SBot.chatColor(conv, "✓ - Félicitations, tu as réussi l'énigme ! LA LETTRE EST ‘T’", SBot.GREEN);
					SBot.chatToMe("✓ - Enigme n°"+(SBot.LEVEL-1)+" réussie à {time}!", true, false);
					if(!SBot.TIP)SBot.chatColor(conv, "L'indice était: « "+SBot.INDICES[SBot.LEVEL-2]+" »", SBot.BLUE);
					SBot.chat(conv, SBot.LEVEL+": "+SBot.ENIGMES[SBot.LEVEL-1], false, true, false);				
					SBot.TIP = false;
				}
				break;
			case 7:
				/* --------------- QUESTION SPECIALE MOT CORRECT --------------- */				
				if(msg.equalsIgnoreCase("indice")){
					if(!SBot.TIP){
						SBot.chatColor(conv, "▸ "+SBot.INDICES[SBot.LEVEL-1], SBot.BLUE);
						SBot.TIP = true;
					}else{
						SBot.chatColor(conv, SBot.ALREADY, SBot.RED);
					}
				}else if(msg.equalsIgnoreCase(SBot.RAND.substring(SBot.LEVEL-1, SBot.LEVEL))){
					SBot.LEVEL++;
					SBot.jf.showText("Enigmes réussies");
					SBot.chatToMe("✓ - Toutes les énigmes réussies à {time}!", true, false);
					SBot.chatColor(conv, "✓ - Bravooo, tu as réussi toutes les énigmes ! Tape le mot correct !", SBot.GREEN);
					if(!SBot.TIP)SBot.chatColor(conv, "L'indice était: « "+SBot.INDICES[SBot.LEVEL-2]+" »", SBot.BLUE);
					SBot.chat(conv, "Pour rappel, les lettres sont: "+SBot.RAND, true, false, false);
					SBot.TIP = false;
				}
				break;
			case 8:
				/* --------------- FIN DE LA PARTIE --------------- */
				if(msg.equalsIgnoreCase(SBot.WORD)){
					SBot.FINISH = true;
					SBot.END = new Date();
					SBot.jf.showText("Jeu terminé");
					SBot.chatColor(conv, "✓ - Youpiiie, tu as trouvé le dernier endroit ! Rejoins-moi vite ♥", SBot.GREEN);
					
					long duration = SBot.END.getTime() - SBot.START.getTime();
					long min = TimeUnit.MILLISECONDS.toMinutes(duration);
					
					SBot.chat(conv, "▸ Tu as pris "+min+ " minutes pour finir ce jeu", false, true, false);
					SBot.chatToMe("Jeu terminé à {time}! ("+min+" min)", true, false);
					SBot.sk.setVisibility(Visibility.INVISIBLE);
				}else{
					SBot.chatColor(conv, "✗ - Ce n'est malheureusement pas ce mot là.. allez courage !", SBot.RED);
				}
				break;
			default:
				/* --------------- DEROULEMENT ENIGMES CLASSIQUES --------------- */
				if(msg.equalsIgnoreCase("indice")){
					if(!SBot.TIP){
						SBot.chatColor(conv, SBot.INDICES[SBot.LEVEL-1], SBot.BLUE);
						SBot.TIP = true;
					}else{
						SBot.chatColor(conv, SBot.ALREADY, SBot.RED);
					}
				}else if(msg.equalsIgnoreCase(SBot.RAND.substring(SBot.LEVEL-1, SBot.LEVEL))){
					SBot.LEVEL++;
					SBot.jf.showText("Enigme n°"+(SBot.LEVEL));
					SBot.chatColor(conv, SBot.WELL, SBot.GREEN);
					SBot.chatToMe("✓ - Enigme n°"+(SBot.LEVEL-1)+" réussie à {time}!", true, false);
					if(!SBot.TIP)SBot.chatColor(conv, "L'indice était: « "+SBot.INDICES[SBot.LEVEL-2]+" »", SBot.BLUE);
					SBot.chat(conv, SBot.LEVEL+": "+SBot.ENIGMES[SBot.LEVEL-1], false, true, false);				
					SBot.TIP = false;
				}
				break;
			}
			break;
		}
		conv.stopTyping();
	}
}
