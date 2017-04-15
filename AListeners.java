package be.lioche.skype.bot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;

public class AListeners implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if(source.equals(SBot.jf.start)){
			try {
				SBot.jf.showText("Création.");
				SBot.sk = new SkypeBuilder(SBot.username, SBot.password).withAllResources().withExceptionHandler((errorSource, throwable, willShutdown) -> {
					System.out.println("Error: " + errorSource + " " + throwable + " " + willShutdown);
				}).build();

				SBot.jf.showText("Connexion..");
				SBot.sk.login();
				SBot.jf.showText("Initialisation...");
				SBot.sk.getEventDispatcher().registerListener(new Events());
				SBot.sk.subscribe();
				SBot.sk.setVisibility(Visibility.ONLINE);
				SBot.jf.showText("Bot opérationel");
				SBot.jf.showStop();
			} catch (NotParticipatingException | InvalidCredentialsException | ConnectionException e1) {
				e1.printStackTrace();
			}
		}else{
			try {
				SBot.jf.showText("Arrêt en cours.");
				SBot.sk.setVisibility(Visibility.INVISIBLE);
				SBot.sk.logout();
				SBot.jf.showText("Déconnecté..");
				SBot.jf.showStart();
			} catch (ConnectionException e1) {
				e1.printStackTrace();
			}

		}
	}

}
