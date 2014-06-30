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
		//versao 5.5.5 lancada em 29 de junho de 2014 corrige o problema dos widgets nao carregarem ao reiniciar, permite redimensionar os widgets e adiciona um widget de pokemon aleatorio, tambem permite assistir pokemon em tela cheia pelo DroiDex News
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x vers&atilde;o 5.5</h3>\n" +
		"Lan&ccedil;ado dia 23 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Agora voc&ecirc; pode por widgets de TODOS os Pok&eacute;mons na sua tela inicial.</li>\n" +
		"<li>DroiD&eacute;x News.</li>\n" +
		"<li>Novo &iacute;cone.</li>\n" +
		"<li>Na vers&atilde;o 5.1.5 foi adicionada uma op&ccedil;&atilde;o de desativar a splash screen e mudar sua anima&ccedil;&atilde;o.</li>\n" +
		"<li>E algumas outras melhorias.</li>\n" +
		"</ul>\n" +
		//versao 5.1.5 lancada em 21 de junho de 2014 atualiza os dadod de pokemons e adiciona uma opcao de desativar a splash screen e mudar sua animacao
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x vers&atilde;o 5.1</h3>\n" +
		"Lan&ccedil;ado dia 19 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Adicionada a p&aacute;gina de configura&ccedil;&otilde;es, podendo mudar a cor do aplicativo.</li>\n" +
		"<li>Adicionado \"Abrir na Bulbapedia\" e Serebii nos detalhes do Pok&eacute;mon.</li>\n" +
		"<li>Vers&atilde;o em portugu&ecirc;s.</li>\n" +
		"</ul>\n" +
		//versao 5.0.1 lancada em 17 de junho 2014 corrige alguns nomes e adiciona uma funcao de compartilhar
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x vers&atilde;o 5.0</h3>\n" +
		"Lan&ccedil;ada dia 15 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Os Pok&eacute;mon mais recentes anunciados e n&atilde;o anunciados oficialmente da sexta gera&ccedil;&atilde;o, e anunciados para Omega Ruby/Alpha Sapphire at&eacute; 15 de junho de 2014.</li>\n" +
		"<li>Adicionada descri&ccedil;&atilde;o de todos os movimentos da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li>Adicionado todos os sprites da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li>E algumas outras melhorias." +
		"</ul>\n" +
		"<p style=\"text-align: justify;\">Essa foi a primeira vers&atilde;o, completamente baseada no D&eacute;xDroid 3.1, percebi que ele n&atilde;o iria mais ser atualizado ent&atilde;o decidi que eu mesmo iria atualiza-lo, j&aacute; que gostava muito do aplicativo.</p>\n";
	private final static String about = "" +
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x</h3>\n" +
		"<p style=\"text-align: justify;\">DroiD&eacute;x &eacute; uma vers&atilde;o modificada do D&eacute;xDroid, desenvolvida por <a href=\"http://google.com/+AlefeSouza/about \">Alefe Souza</a>.</p>\n" +
		"<p style=\"text-align: justify;\">Eu notei que o D&eacute;xDroid saiu da Play Store e n&atilde;o recebeu mais atualiza&ccedil;&otilde;es, eu n&atilde;o gostei disso j&aacute; que eu gostava muito do D&eacute;xDroid e sempre indiquei para meus amigos, ent&atilde;o eu decidi criar o DroiD&eacute;x, uma vers&atilde;o modificada do D&eacute;xDroid 3.1 com os Pok&eacute;mon mais recentes e mais algumas fun&ccedil;&otilde;es.</p>\n" +
		"<p style=\"text-align: justify;\">Agradecimentos:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li><a href=\"http://pkparaiso.com/\">PkPara&iacute;so</a>, por todos os sprites da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li><a href=\"http://deviantart.com/ \">DeviantArt</a>, pelas Sugimori art e sprites dos Pok&eacute;mons recentemente anunciados e n&atilde;o anunciados oficialmente.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">C&oacute;digo fonte</h3>\n" +
		"<p style=\"text-align: justify;\">Por favor leia a se&ccedil;&atilde;o \"License\" dessa p&aacute;gina, ela continua a mesma do D&eacute;xDroid, voc&ecirc; pode ver o c&oacute;digo fonte do DroiD&eacute;x no meu <a href=\"https://github.com/alefesouza/droidex \">Github</a>. Caso voc&ecirc; use algum c&oacute;digo do DroiD&eacute;x (como os widgets e configu&ccedil;&otilde;es, coisas que n&atilde;o tem no aplicativo original), tamb&eacute;m credite a mim.</p>\n" +
		"<hr>\n" +
		"<h3 style=\"text-align: justify;\">Sobre do D&eacute;xDroid 3.1</h3>\n" +
		"<p style=\"text-align: justify;\">D&eacute;xDroid is Pok&eacute;dex (Pok&eacute;mon encyclopedia) for Android. It contains data of all Pok&eacute;mon species from every series of Pok&eacute;mon game (Gen I to Gen VI). Every Pok&eacute;mon's data is consisted by :</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Image (Sugimori Art and sprites)</li>\n" +
		"<li>Name (English, Japanese, and Romaji name)</li>\n" +
		"<li>Dex number for all region</li>\n" +
		"<li>Description (from all Pok&eacute;mon version)</li>\n" +
		"<li>Height</li>\n" +
		"<li>Weight</li>\n" +
		"<li>Ability</li>\n" +
		"<li>Type efficacy</li>\n" +
		"<li>Base stat</li>\n" +
		"<li>Move (separated by version group)</li>\n" +
		"<li>Location (separated by Pok&eacute;mon version)</li>\n" +
		"<li>Evolution (including Mega Evolution)</li>\n" +
		"<li>Etc.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">License</h3>\n" +
		"<p style=\"text-align: justify;\">This app is released under <a href=\"http://choosealicense.com/licenses/gpl-v3/\">GPLv3 License</a> and its source code is available in my <a href=\"https://github.com/Acrophobic/Pokedex\">Github</a>. Everyone is allowed to modify this app and release it in their own name, but they have to open the source code. And, if you would, please give proper credit to me.</p>\n" +
		"<h3 style=\"text-align: justify;\">Sources</h3>\n" +
		"<p style=\"text-align: justify;\">Database is taken from Veekun's git (<a href=\"http://git.veekun.com/pokedex.git/tree/HEAD:/pokedex/data/csv\">here</a> or <a href=\"https://github.com/veekun/pokedex/tree/master/pokedex/data/csv\">here</a>). I just convert it from CSV to SQLITE.</p>\n" +
		"<p style=\"text-align: justify;\">Images and sprites are taken from :</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Veekun's <a href=\"http://git.veekun.com/pokedex-media.git\">git</a> and his <a href=\"http://veekun.com/dex/downloads\">site</a></li>\n" +
		"<li><a href=\"http://bulbapedia.bulbagarden.net\">Bulbapedia</a>, the community driven Pok&eacute;mon encyclopedia</li>\n" +
		"<li><a href=\"http://www.legendarypokemon.net/\">LegendaryPokemon</a>, where I took some of Sugimori Art</li>\n" +
		"<li><a href=\"http://www.serebii.net\">Serebii</a>, where I took some sprites for Pok&eacute;mon X/Y and Mega Evolution</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Acknowledgements</h3>\n" +
		"<p style=\"text-align: justify;\">In this app, I use following library / tools :</p>\n" +
		"<ul>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jgilfelt/android-sqlite-asset-helper\">Android SQLiteAssetHelper</a> library by <a href=\"https://github.com/jgilfelt\">Jeff Gilfelt</a></li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jeromevdl/android-holo-colors-idea-plugin\">Android Holo Colors Plugin</a>&nbsp;by <a href=\"https://github.com/jeromevdl\"><span class=\"vcard-fullname\">J&eacute;r&ocirc;me Van Der Linden</span></a></li>\n" +
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
