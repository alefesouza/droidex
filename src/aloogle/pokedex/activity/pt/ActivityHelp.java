package aloogle.pokedex.activity.pt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import aloogle.pokedex.R;

public class ActivityHelp extends Activity {
	private final static String help = "" +
		"<h3 style=\"text-align: justify;\">FAQ</h3>\n" +
		"<p style=\"text-align: justify;\"><b>ISSO N&Atilde;O &Eacute; TERMOS DE USO, LEIA</b> antes de perguntar qualquer coisa.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Por que as imagens n&atilde;o carregam (aparecem apenas Missingnos)?</b></p>\n" +
		"<p style=\"text-align: justify;\">As imagens n&atilde;o s&atilde;o inclu&iacute;das no aplicativo para deixa-lo mais leve, mas voc&ecirc; pode baixa-las indo em Menu (os tr&ecirc;s quadradinhos no canto superior direito se seu celular n&atilde;o tiver bot&otilde;es, e o bot&atilde;o a esquerda do bot&atilde;o central se tiver) e tocando em \"Baixar imagens\".</p>\n" +
		"<p style=\"text-align: justify;\"><b>Apareceu \"Sugimori art\" e \"Sprite\", mas qual eu devo baixar e qual a diferen&ccedil;a entre os dois?</b></p>\n" +
		"<p style=\"text-align: justify;\">Sprites s&atilde;o as imagens pequenas e Sugimori art as grandes, por isso elas s&atilde;o bem mais pesadas, entenda que se voc&ecirc; baixar s&oacute; os sprites as imagens grandes n&atilde;o v&atilde;o aparecer, assim como se voc&ecirc; baixar s&oacute; as Sugimori art as imagens pequenas n&atilde;o v&atilde;o aparecer, se voc&ecirc; quiser tudo funcionando direitinho, baixe as duas.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Por que n&atilde;o devo fechar o aplicativo durante o download?</b></p>\n" +
		"<p style=\"text-align: justify;\">O aplicativo baixa as imagens em um .zip e as extrai ao fim do download, e n&atilde;o tem como um aplicativo fazer algo ao final do download se estiver fechado (a n&atilde;o ser que seja aqueles aplicativos chatos que voc&ecirc; nunca abre e mesmo for&ccedil;ando parada voltam do al&eacute;m pra rodar em segundo plano). As vezes o Android fecha os aplicativos minimizados, isso &eacute; do pr&oacute;prio sistema, ent&atilde;o se poss&iacute;vel nem sequer minimize-o durante o download.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Baixei tudo mas ainda aparecem Missingnos</b></p>\n" +
		"<p style=\"text-align: justify;\">Normalmente eles aparecem em Pok&eacute;mons recentes porque ainda n&atilde;o tem sprite oficial.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Como adiciono Pok&eacute;mons na minha tela inicial?</b></p>\n" +
		"<p style=\"text-align: justify;\">Primeiro baixe as Sugimori art, depois v&aacute; no menu de todos os aplicativos do seu dispositivo e toque em Widgets, depois pressione \"Adicionar Pok&eacute;mon\" (caso n&atilde;o haja Widgets nesse menu, pressione uma parte vazia da sua tela inicial por um segundo e toque em Widgets).</p>\n" +
		"<p style=\"text-align: justify;\"><b>O aplicativo est&aacute; em ingl&ecirc;s?????</b></p>\n" +
		"<p style=\"text-align: justify;\">Leva muuuuuito tempo para traduzir todas as descri&ccedil;&otilde;es de Pok&eacute;mons, movimentos, habilidades, etc da primeira a sexta gera&ccedil;&atilde;o, mas alguns usu&aacute;rios do DroiD&eacute;x j&aacute; est&atilde;o trabalhando nisso,  uma vers&atilde;o totalmente em portugu&ecirc;s sair&aacute; em breve.</p>\n" +
		"<p style=\"text-align: justify;\">Caso ainda queira fazer alguma outra pergunta, use a op&ccedil;&atilde;o \"Enviar opini&atilde;o\" do aplicativo</p>\n";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changelog_about);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColor = preferences.getString("prefColor", "droidexblue");
		if (userColor.equals("red"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff0000));
		else if (userColor.equals("green"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00ff00));
		else if (userColor.equals("blue"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0000ff));
		else if (userColor.equals("yellow"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffffff00));
		else if (userColor.equals("gold"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffdaa520));
		else if (userColor.equals("silver"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffc0c0c0));
		else if (userColor.equals("crystal"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffa1e2ff));
		else if (userColor.equals("ruby"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe0115f));
		else if (userColor.equals("sapphire"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0f52ba));
		else if (userColor.equals("emerald"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff50c878));
		else if (userColor.equals("diamond"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffb9f2ff));
		else if (userColor.equals("pearl"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffeae0c8));
		else if (userColor.equals("platinum"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe5e4e2));
		else if (userColor.equals("black"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000));
		else if (userColor.equals("droidexblue"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0080ff));
		else if (userColor.equals("dexdroidred"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff4444));

		String userIcon = preferences.getString("prefIcon", "default");
		if (userIcon.equals("default"))
			getActionBar().setIcon(R.drawable.ic_launcher);
		else if (userIcon.equals("red"))
			getActionBar().setIcon(R.drawable.ic_pokedex);
		else if (userIcon.equals("green"))
			getActionBar().setIcon(R.drawable.ic_abilitydex);
		else if (userIcon.equals("blue"))
			getActionBar().setIcon(R.drawable.ic_itemdex);
		else if (userIcon.equals("yellow"))
			getActionBar().setIcon(R.drawable.ic_movedex);

		TextView txtOK = (TextView)findViewById(R.id.txtOkChngAbout);
		WebView viewChangelog = (WebView)findViewById(R.id.viewAboutChangelog);

		setTitle(getString(R.string.help));
		viewChangelog.loadData(help, "text/html", "UTF-8");

		txtOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
				if (tabletSize) {
					Intent intent = new Intent(ActivityHelp.this, aloogle.pokedex.activity.ActivityMainTablet.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(ActivityHelp.this, aloogle.pokedex.activity.ActivityMain.class);
					startActivity(intent);
				}
				finish();
			}
		});
	}
	public void onBackPressed() {
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			Intent intent = new Intent(ActivityHelp.this, aloogle.pokedex.activity.ActivityMainTablet.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(ActivityHelp.this, aloogle.pokedex.activity.ActivityMain.class);
			startActivity(intent);
		}
		ActivityHelp.this.finish();
	}
}
