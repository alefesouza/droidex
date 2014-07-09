package aloogle.pokedex.activity.pt;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class ActivityAboutChangelog extends Activity {
	private final static String changelog = "" +
		"<h3 style=\"text-align: justify;\">Vers&atilde;o 5.5</h3>\n" +
		"Lan&ccedil;ado dia 23 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Agora voc&ecirc; pode por widgets de TODOS os Pok&eacute;mons na sua tela inicial.</li>\n" +
		"<li>DroiD&eacute;x News.</li>\n" +
		"<li>Novo &iacute;cone.</li>\n" +
		"<li>E algumas outras melhorias.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.5.5:</b> Lan&ccedil;ada em 29 de junho de 2014 - Corrige o problema dos widgets n&atilde;o carregarem ao reiniciar, permite redimensionar os widgets e adiciona um widget de Pok&eacute;mon aleat&oacute;rio, tamb&eacute;m permite assistir Pok&eacute;mon em tela cheia pelo DroiD&eacute;x News.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.5.7:</b> Lan&ccedil;ada em 09 de julho de 2014 - Adiciona op&ccedil;&atilde;o de abrir no Smogon, um novo menu lateral nos smartphones para deixar o aplicativo mais organizado e corrige bugs.</p>\n" +
		"<h3 style=\"text-align: justify;\">Vers&atilde;o 5.1</h3>\n" +
		"Lan&ccedil;ado dia 19 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Adicionada a p&aacute;gina de configura&ccedil;&otilde;es, podendo mudar a cor do aplicativo.</li>\n" +
		"<li>Adicionado \"Abrir na Bulbapedia\" e Serebii nos detalhes do Pok&eacute;mon.</li>\n" +
		"<li>Vers&atilde;o em portugu&ecirc;s.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.1.5:</b> Lan&ccedil;ada em 21 de junho de 2014 - Atualiza os dados de Pok&eacute;mon e adiciona uma op&ccedil;&atilde;o de desativar a splash screen, e mudar sua anima&ccedil;&atilde;o tamb&eacute;m.</p>\n" +
		"<h3 style=\"text-align: justify;\">Vers&atilde;o 5.0</h3>\n" +
		"Lan&ccedil;ada dia 15 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Os Pok&eacute;mon mais recentes anunciados e n&atilde;o anunciados oficialmente da sexta gera&ccedil;&atilde;o, e anunciados para Omega Ruby/Alpha Sapphire at&eacute; 15 de junho de 2014.</li>\n" +
		"<li>Adicionada descri&ccedil;&atilde;o de todos os movimentos da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li>Adicionado todos os sprites da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li>E algumas outras melhorias." +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.0.1:</b> Lan&ccedil;ada em 17 de junho 2014 - Corrige alguns nomes e adiciona uma fun&ccedil;&atilde;o de compartilhar o aplicativo.</p>\n" +
		"<p style=\"text-align: justify;\">Essa foi a primeira vers&atilde;o, completamente baseada no D&eacute;xDroid 3.1, percebi que ele n&atilde;o iria mais ser atualizado ent&atilde;o decidi que eu mesmo iria atualiza-lo, j&aacute; que gostava muito do aplicativo.</p>\n";
	private final static String about = "" +
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x</h3>\n" +
		"<p style=\"text-align: justify;\">DroiD&eacute;x &eacute; um aplicativo de Pok&eacute;dex baseado no D&eacute;xDroid, desenvolvido por <a href=\"http://google.com/+AlefeSouza/about \">Alefe Souza</a>.</p>\n" +
		"<p style=\"text-align: justify;\">Eu notei que o D&eacute;xDroid n&atilde;o receberia mais atualiza&ccedil;&otilde;es, eu n&atilde;o gostei disso j&aacute; que eu gostava muito do aplicativo e sempre indiquei para meus amigos, ent&atilde;o eu decidi criar o DroiD&eacute;x, um aplicativo baseado no D&eacute;xDroid 3.1 com os Pok&eacute;mon mais recentes e mais algumas fun&ccedil;&otilde;es.</p>\n" +
		"<p style=\"text-align: justify;\">Por ser baseado nele, possui as mesmas fun&ccedil;&otilde;es, como dados de todas as esp&eacute;cies de Pok&eacute;mon de todos os jogos (da primeira gera&ccedil;&atilde;o a sexta gera&ccedil;&atilde;o), todos os dados de Pok&eacute;mon consistem em:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Imagem (Sugimori Art e sprites)</li>\n" +
		"<li>Nome (Ingl&ecirc;s, Japon&ecirc;s e nome Romaji)</li>\n" +
		"<li>N&uacute;mero da Pok&eacute;dex de todas as regi&otilde;es </li>\n" +
		"<li>Descri&ccedil;&atilde;o (de todas as vers&otilde;es de Pok&eacute;mon)</li>\n" +
		"<li>Altura</li>\n" +
		"<li>Peso</li>\n" +
		"<li>Habilidade</li>\n" +
		"<li>Efic&aacute;cia de tipo</li>\n" +
		"<li>Status base</li>\n" +
		"<li>Movimentos (separados por grupo de vers&atilde;o)</li>\n" +
		"<li>Localiza&ccedil;&atilde;o (separada por vers&atilde;o de Pok&eacute;mon)</li>\n" +
		"<li>Evolu&ccedil;&atilde;o (incluindo Mega Evolu&ccedil;&atilde;o)</li>\n" +
		"<li>Etc.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\">As principais diferen&ccedil;as se comparado ao D&eacute;xDroid s&atilde;o uma p&aacute;gina de configura&ccedil;&otilde;es para mudar a cor do aplicativo, e poder adicionar widgets de QUALQUER Pok&eacute;mon na tela inicial.</p>\n" +
		"<h3 style=\"text-align: justify;\">Licen&ccedil;a</h3>\n" +
		"<p style=\"text-align: justify;\">Esse aplicativo foi lan&ccedil;ado sob <a href=\"http://choosealicense.com/licenses/gpl-v3/\">licen&ccedil;a GPLv3</a> e o c&oacute;digo fonte dele est&aacute; dispon&iacute;vel no meu <a href=\"https://github.com/alefesouza/droidex\">GitHub</a>. Todo mundo esta permitido a modificar e lan&ccedil;ar esse aplicativo em seu nome, mas vai ter que liberar o c&oacute;digo fonte. E, se voc&ecirc; fizer isso, por favor d&ecirc; os devidos cr&eacute;ditos a mim, <a href=\"https://github.com/alefesouza\">Alefe Souza</a>, e ao desenvolvedor do D&eacute;xDroid, <a href=\"https://github.com/RadhiFadlillah\">Radhi</a>.</p>\n" +
		"<h3 style=\"text-align: justify;\">Fontes</h3>\n" +
		"<p style=\"text-align: justify;\">Base de dados foi tirada de Veekun's git (<a href=\"http://git.veekun.com/pokedex.git/tree/HEAD:/pokedex/data/csv\">aqui</a> ou <a href=\"https://github.com/veekun/pokedex/tree/master/pokedex/data/csv\">aqui</a>). Foi apenas convertido de CSV para SQLITE.</p>\n" +
		"<p style=\"text-align: justify;\">Imagens e sprites foram tiradas de:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Veekun's <a href=\"http://git.veekun.com/pokedex-media.git\">git</a> e seu <a href=\"http://veekun.com/dex/downloads\">site</a>.</li>\n" +
		"<li><a href=\"http://bulbapedia.bulbagarden.net\">Bulbapedia</a>, a comunidade dirigida a enciclop&eacute;dia Pok&eacute;mon.</li>\n" +
		"<li><a href=\"http://www.legendarypokemon.net/\">LegendaryPokemon</a>, por algumas Sugimori Art.</li>\n" +
		"<li><a href=\"http://www.serebii.net\">Serebii</a>, por alguns sprites do Pok&eacute;mon X/Y e Mega Evolu&ccedil;&otilde;es.</li>\n" +
		"<li><a href=\"http://pkparaiso.com/\">PkPara&iacute;so</a>, por todos os sprites da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li><a href=\"http://deviantart.com/ \">DeviantArt</a>, pelas Sugimori Art e sprites dos Pok&eacute;mons recentemente anunciados e n&atilde;o anunciados oficialmente.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Agradecimentos</h3>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/RadhiFadlillah\">Radhi</a>, desenvolvedor do D&eacute;xDroid.</li>\n" +
		"<p style=\"text-align: justify;\">Nesse aplicativo foi usado as seguintes biblioteca/ferramentas:</p>\n" +
		"<ul>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jgilfelt/android-sqlite-asset-helper\">Android SQLiteAssetHelper</a> biblioteca por <a href=\"https://github.com/jgilfelt\">Jeff Gilfelt</a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jeromevdl/android-holo-colors-idea-plugin\">Android Holo Colors Plugin</a> por <a href=\"https://github.com/jeromevdl\"><span class=\"vcard-fullname\">J&eacute;r&ocirc;me Van Der Linden</span></a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://code.google.com/p/giflib/\">Animated GIF ImageView Library for Android</a> por <a href=\"http://abhinavasblog.blogspot.com.br/2014/04/animated-gif-imageview-library-for.html\"><span class=\"vcard-fullname\">Abhinava Srivastava</span></a>.</li>\n" +
		"</ul>";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changelog_about);
		getWindow().setBackgroundDrawable(null);
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

		int AboutOrChange = getIntent().getIntExtra(Other.AboutOrChange, 0);

		if (AboutOrChange == 0) {
			setTitle("Changelog");
			viewChangelog.loadData(changelog, "text/html", "UTF-8");
		} else {
			setTitle(getString(R.string.about));
			viewChangelog.loadData(about, "text/html", "UTF-8");
		}

		txtOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
