package be.lioche.skype.bot;

public enum Secrets {
	
	ANNIVERSAIRE("HAPPY BIRTHDAY TO YOUUUU ! (cake)", false),
	BB_LOU("Rhoo, je t'ai dit que j'étais un renard, moi ! Hihi :3", false),
	BEAUTICU("Félicitation, tu as retenu ! Ou alors simplement cherché sur fb.. Tu gagnes un calin ! (hug)", false),
	BY("61.11.11 ou 71.30.62 ? Telle est la question. (ou pas hihi)", false),
	JE_SAIS_PAS("Moi non plus, zut alors !", false),
	JUNIPER("Hé psst, si tu viens me dire ‘juni bébé renard’, tu auras droit à un cadeau, mais chuut!", false),
	JUNI_BB("Nan mais pas à moi, idiote ! A ton Lioche d'amour, grr", false),
	LOVE("Mwa ochi je t'aime mon chaaaat ♥", false),
	POULET("Raté ! Moi je suis un RENARD !", false),
	RENARD("What does the fox say ? Ring-ding-ding-ding-dingeringeding! And what's his name ?", false),
	SECRET("Oui oui, secret secret !", false),
	TF_INDICE("Indice pour un autre secret: quel est le mot que je t’ai demandé de retenir à propos de The Finder ?", false),
	THE_FINDER("C'est très bien, tu sais lire, mais moi j'attend toujours le mot !", false);
		
	private String message;
	private boolean founded;
	
	private Secrets(String message, boolean found){
		this.message = message;
		this.founded = found;
	}
	
	public void setFound(){
		this.founded = true;
	}
	
	public String get(){
		return this.message;
	}
	
	public boolean isFound(){
		return this.founded;
	}
}
