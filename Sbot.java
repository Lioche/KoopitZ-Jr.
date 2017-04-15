package be.lioche.skype.bot;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.formatting.Message;
import com.samczsun.skype4j.formatting.Text;

public class SBot {
	
	static String username = "";
	static String password = "";
	
	static Skype sk;
	static SimpleDateFormat df = new SimpleDateFormat("dd/MM à HH:mm");
	
	static SFrame jf;
	static JPanel jp = new JPanel();
	
	/* --------------------------------------------------------- */
	
	static Date START;
	static Date END;
	static int LEVEL = 0;
	static boolean TIP = false;
	static boolean WAIT = false;
	static boolean FINISH = false;
	static boolean CANPLAY = false;
	
	static Color GREEN = new Color(20,155,20);
	static Color RED = new Color(130,0,0);
	static Color BLUE = new Color(0,39,130);
	
	static String WORD = "athénée";
	static String RAND = "ehéténa";
	
	static String NB = "164";
	
	static String WELL = "✓ - Félicitations, tu as réussi l'énigme ! Voici la suivante:";
	static String ALREADY = "✗ - L'indice de cette énigme t'a déjà été révélé.";
	static String SECRET = "✗ - Le secret t'a déjà été révélé.";
	
	static String[] ENIGMES = {
			"Nous sommes le couple qui vous observe là où il te raccompagne.",
			"Mémorial je m’appelle. Dos au château je reste. Entourés de fleurs je suis. La montagne je précède.",
			"Fondée en 1986, je possède une cour typique des maisons andalousiennes.",
			"Combien de jours se sont écoulés depuis notre premier bisou à tes 18 ans ? (Quel est le nombre?)",
			"Le matin tu me rejoins. Soulagée de me quitter tu es. Mais contente de venir tu dois être.",
			"Des centaines tu en trouveras, mais une seule lettre tu choisiras.",
			"Violet est ma couleur. Apprécié, je suis. Mais plus encore lorsque je ne suis plus là. Acheté j’ai été, où ça ? "
	};
	
	static String[] INDICES = {
			"‘Les Dorlodos’",
			"Je t’attends tout près de la maison de la Gadale",
			"Si je te dis ‘Patio’ ?",
			"La réponse est l'arrondi de √713062 - √611111 + √0.09 + le nombre de dalmatiens dans le titre du film de Disney (1961)",
			"Seulement les jours de la semaine, à 8h",
			"Proche des personnes âgées, des livres je contiens. Un donné pour un rendu",
			"Sous-vêtement"
	};

	public static void main(String[] args){
		jf = new SFrame("KoopitZBot", 300, 100, false, jp);
		jf.setVisible(true);
	}

	public static void chatError(Chat conv, String error){
		chatColor(conv, "[Erreur] "+error, Color.red);
	}
	
	public static String specialText(String base){
		String back;
		back = base.replace("{time}", new SimpleDateFormat("HH:mm").format(new Date()))
				.replace("{date}", new SimpleDateFormat("dd-MM-yy").format(new Date()));
		return back;
	}

	public static void chatMultiple(Chat conv, String... msgs){
		SBot.WAIT = true;
		try{
			for(String s : msgs){
				s = specialText(s);
				conv.sendMessage(s);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					chatError(conv, "Problème de cooldown");
				}
			}
		}catch(ConnectionException e){
			e.printStackTrace();
		}
		SBot.WAIT = false;
	}

	public static void chat(Chat conv, String s, boolean bold, boolean italic, boolean under){
		SBot.WAIT = true;
		try {
			s = specialText(s);
			Text text = Text.plain(s);
			if(bold)text = Text.rich(s).withBold();
			if(italic)text = Text.rich(text.toString()).withItalic();
			if(under)Text.rich(text.toString()).withUnderline();
			conv.sendMessage(Message.create().with(text));
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		SBot.WAIT = false;
	}
	
	public static void chatToMe(String s, boolean bold, boolean italic){
		try {
			Chat conv = sk.getOrLoadContact("liochelamyche").getPrivateConversation();
			s = specialText(s);
			Text text = Text.plain(s);
			if(bold)text = Text.rich(s).withBold();
			if(italic)text = Text.rich(text.toString()).withItalic();
			conv.sendMessage(Message.create().with(text));
		} catch (ConnectionException | ChatNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void chatColor(Chat conv, String s, Color color){
		SBot.WAIT = true;
		try {
			Text text = Text.rich(specialText(s)).withColor(color);
			conv.sendMessage(Message.create().with(text));
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		SBot.WAIT = false;
	}
}
