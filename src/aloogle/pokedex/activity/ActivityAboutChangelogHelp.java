package aloogle.pokedex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class ActivityAboutChangelogHelp extends Activity {
	private final static String changelog = "" +
		"<h3 style=\"text-align: justify;\">Version 5.9</h3>\n" +
		"Launched September, 27, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Sprites/gifs/animated Pok&eacute;mon models.</li>\n" +
		"<li>Added all announced Pok&eacute;mon until 09/27/2014.</li>\n" +
		"<li>New images and souds download improved.</li>\n" +
		"<li>Interface improvement.</li>\n" +
		"<li>And other improvements.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.9.1:</b> Launched September, 29, 2014 - Fixed crash on open Gastly.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.9.3:</b> Launched October, 05, 2014 - Added Mega Rayquaza.</p>\n" +
		"<h3 style=\"text-align: justify;\">Version 5.7</h3>\n" +
		"Launched July, 15, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Pok&eacute;mon cries (in-game cries and anime cries).</li>\n" +
		"<li>Now Pok&eacute;mon details page change its color according to the selected Pok&eacute;mon.</li>\n" +
		"<li>New navigation menu in DroiD&eacute;x News.</li>\n" +
		"<li>And some others improvements.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Version 5.5</h3>\n" +
		"Launched June, 23, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Place widgets of ALL Pok&eacute;mon on your homescreen.</li>\n" +
		"<li>DroiD&eacute;x News.</li>\n" +
		"<li>New app icon.</li>\n" +
		"<li>And some other improvements.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.5.5:</b> Launched June, 29, 2014 - Fixed widgets not loading after restarting the device, widget resizing and random Pok&eacute;mon widget, also watching Pok&eacute;mon in fullscreen on DroiD&eacute;x News.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.5.7:</b> Launched July, 09, 2014 - Added open in Smogon option, new sidebar in smartphones (for a more organized app menu), added Fancy Vivillon and fixed some bugs. Version <i>5.5.7.1</i> launched in July, 11, 2014 add Mega Metagross.</p>\n" +
		"<h3 style=\"text-align: justify;\">Version 5.1</h3>\n" +
		"Launched June, 19, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Settings page added, with app color and icon changing options.</li>\n" +
		"<li>\"Open on Bulbapedia\" and Serebii added in Pok&eacute;mon details.</li>\n" +
		"<li>Portuguese version.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.1.5:</b> Launched June, 21, 2014 - Pok&eacute;mon data updated, splash screen deactivation option and its animation type changing option added.</p>\n" +
		"<h3 style=\"text-align: justify;\">Version 5.0</h3>\n" +
		"Launched June, 15, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Newest Pok&eacute;mons announced and unannounced officially from 6th generation, and announced for Omega Ruby/Alpha Sapphire until June, 15, 2014.</li>\n" +
		"<li>Added all 6th moves description.</li>\n" +
		"<li>Added all 6th generation sprites.</li>\n" +
		"<li>And some others improvements.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.0.1:</b> Launched June, 17, 2014 - Fixed some names and added a share the app option.</p>\n" +
		"<p style=\"text-align: justify;\">This was the first version totally based on D&eacute;xDroid 3.1, I realized that it wouldn't be updated anymore so I decided updating the app by myself, since I liked too much D&eacute;xDroid.</p>\n";
	private final static String about = "" +
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x</h3>\n" +
		"<p style=\"text-align: justify;\">DroiD&eacute;x is a Pok&eacute;dex app based on D&eacute;xDroid, developed by <a href=\"http://google.com/+AlefeSouza/about \">Alefe Souza</a>.</p>\n" +
		"<p style=\"text-align: justify;\">I noticed that the D&eacute;xDroid wouldn't receive updates anymore, I did not like this because I loved D&eacute;xDroid and always indicated to my friends, then I decided create DroiD&eacute;x, a new app based on D&eacute;xDroid 3.1 with most recent Pok&eacute;mon updates and some others functions.</p>\n" +
		"<p style=\"text-align: justify;\">Since is based on D&eacute;xDroid, it has the same functions, as data of all Pok&eacute;mon species from every series of Pok&eacute;mon game (Gen I to Gen VI), every Pok&eacute;mon's data is consisted by:</p>\n" +
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
		"<p style=\"text-align: justify;\">Compared to the D&eacute;xDroid, the major differences are a settings page to change the theme color, the function to add widgets of ANY Pok&eacute;mon onto your home screen, Pok&eacute;mon anime and game sounds and Pok&eacute;mon XY animated gifs of ALL Pok&eacute;mon.</p>\n" +
		"<h3 style=\"text-align: justify;\">License</h3>\n" +
		"<p style=\"text-align: justify;\">This app is released under <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=license\">GPLv3 License</a> and its source code is available in my <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=droidexgithub\">Github</a>. Everyone is allowed to modify this app and release it in their own name, but they have to open the source code. And, if you would, please give proper credit to me, <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=alefegithub\">Alefe Souza</a>, and the developer of D&eacute;xDroid, <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=radhigithub\">Radhi Fadlillah</a>.</p>\n" +
		"<h3 style=\"text-align: justify;\">Sources</h3>\n" +
		"<p style=\"text-align: justify;\">Database is taken from Veekun's git (<a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun1\">here</a> or <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun2\">here</a>). Were just converted it from CSV to SQLITE.</p>\n" +
		"<p style=\"text-align: justify;\">Images and sprites are taken from:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Veekun's <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun3\">git</a> and his <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun4\">site</a>.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=bulbapedia\">Bulbapedia</a>, the community driven Pok&eacute;mon encyclopedia.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=legendarypokemon/\">LegendaryPokemon</a>, for some Sugimori Art.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=serebii\">Serebii</a>, for some sprites for Pok&eacute;mon X/Y and Mega Evolution.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=pkparaiso\">PkPara&iacute;so</a>, for all the sprites of 6th generation.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=deviantart\">DeviantArt</a>, for all Sugimori Art and sprites of most recent Pok&eacute;mons announced and unannounced officially.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\">Cries of anime and game are taken from:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=pokemon3d\">Pok&eacute;mon 3D Forum</a>, for all anime cries.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun4\">Veekun</a>, for all game cries.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Acknowledgements</h3>\n" +
		"<li style=\"text-align: justify;\"><a href=\"aloogle.tumblr.com/droidex/redirects?redirect=radhigithub\">Radhi Fadlillah</a>, developer of D&eacute;xDroid.</li>\n" +
		"<p style=\"text-align: justify;\">In this app, were used following library/tools:</p>\n" +
		"<ul>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=sqlhelper\">Android SQLiteAssetHelper</a> and <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=systembartint\">SystemBarTint</a> libraries by <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=jgilfelt\">Jeff Gilfelt</a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=holocolors\">Android Holo Colors Plugin</a>&nbsp;by <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=jeromevdl\"><span class=\"vcard-fullname\">J&eacute;r&ocirc;me Van Der Linden</span></a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=giflib\">Animated GIF ImageView Library for Android</a> by <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=abhinava\"><span class=\"vcard-fullname\">Abhinava Srivastava</span></a>.</li>\n" +
		"</ul>";
	private final static String help = "" +
		"<p style=\"text-align: justify;\"><b>Why there is no images, only Missingnos?</b></p>\n" +
		"<p style=\"text-align: justify;\">The image data is not included in the application pack to make it lighter, but it is possible to download in-app: tap Menu (the tree little squares on the right top, or if you have a phone wich has a Menu button, press it), after tap \"Download image\".</p>\n" +
		"<p style=\"text-align: justify;\"><b>Ok. I did it all, but appeared \"Sugimori art\" and \"Sprite\", wich one is the right one? What\'s the difference between one another?</b></p>\n" +
		"<p style=\"text-align: justify;\">Sprites are the little image that appear in-batte and Sugimori art the big ones, that\'s why they\'re such heavier, notice that if you only download the sprites, big image won\'t appear, and if you only download Sugimori art the tiny ones won\'t appear too, if you want it all working download both.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Why shouldn\'t I close the app while download is in progress? </b></p>\n" +
		"<p style=\"text-align: justify;\">The app downloads zipped image files that are extracted in the end of the download. Since there is no way to do anything with an closed app, the app won't extract if closed (unless that boring apps that you never open and are always working). Sometimes Android system closes minimized applications, than if possible don\'t even minimize the app during the download.</p>\n" +
		"<p style=\"text-align: justify;\"><b>I have all the images but the Missingnos keep appearing</b></p>\n" +
		"<p style=\"text-align: justify;\">Normally, recently announced and unannounced officially Pok&eacute;mon don't have it.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Why gifs take a lot of time to load?</b></p>\n" +
		"<p style=\"text-align: justify;\">Animated gifs are heavy files, don't think your mobile internet plan can handle that. Load it preferentially over an Wi-Fi connection.</p>" +
		"<p style=\"text-align: justify;\"><b>Why there is no option to download the gif files? </b></p>" +
		"<p style=\"text-align: justify;\">As said before, animated gifs are heavy files. Them all together occupy about 1Gb.<p>\n" +
		"<p style=\"text-align: justify;\"><b>How can I put Pok&eacute;mon in my homescreen?</b></p>\n" +
		"<p style=\"text-align: justify;\">First, download the Sugimori art, after go to the app drawer of your device and tap Widgets, and press \"Add Pok&eacute;mon\" (if there are no Widgets in this menu, press for a while an empty spot in your homescreen and then tap Widgets).</p>" +
		"<p style=\"text-align: justify;\"><b>Why Pok&eacute;mon cries take a lot of time to load or sometimes don\'t load?</b></p>" +
		"<p style=\"text-align: justify;\">The application needs downloading the audio to then play it, and this can take a while depending on your internet connection. If you want it to play imediatelly and without internet connection, download all cries going to Menu > Download sounds, and when it does not appears it\'s because the Pok&eacute;mon hasn\'t a cry yet.</p>";
	private final static String changelogpt = "" +
		"<h3 style=\"text-align: justify;\">Vers&atilde;o 5.9</h3>\n" +
		"Lan&ccedil;ado dia 27 de setembro de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Sprites/gifs/modelos animados de todos os Pok&eacute;mon.</li>\n" +
		"<li>Adicionados todos os Pok&eacute;mons anunciados at&eacute; 27 de setembro de 2014.</li>" +
		"<li>Novo \"V&iacute;deos relacionados\".</li>\n" +
		"<li>Download de novas imagens e sons facilitado.</li>\n" +
		"<li>Melhoras na interface.</li>\n" +
		"<li>E muitas outras melhorias.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.9.1:</b> Lan&ccedil;ada em 29 de setembro de 2014 - Corrige problema de o aplicativo fechar ao abrir o Gastly.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.9.3:</b> Lan&ccedil;ada em 05 de outubro de 2014 - Adicionado Mega Rayquaza.</p>\n" +
		"<h3 style=\"text-align: justify;\">Vers&atilde;o 5.7</h3>\n" +
		"Lan&ccedil;ado dia 15 de julho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Sons dos Pok&eacute;mon (no anime e no jogo).</li>\n" +
		"<li>Agora a p&aacute;gina de detalhes muda de acordo com a cor do Pok&eacute;mon.</li>\n" +
		"<li>Novo menu de navega&ccedil;&atilde;o no DroiD&eacute;x News.</li>\n" +
		"<li>E algumas outras melhorias.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Vers&atilde;o 5.5</h3>\n" +
		"Lan&ccedil;ado dia 23 de junho de 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Agora voc&ecirc; pode por widgets de TODOS os Pok&eacute;mons na sua tela inicial.</li>\n" +
		"<li>DroiD&eacute;x News.</li>\n" +
		"<li>Novo &iacute;cone.</li>\n" +
		"<li>E algumas outras melhorias.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.5.5:</b> Lan&ccedil;ada em 29 de junho de 2014 - Corrige o problema dos widgets n&atilde;o carregarem ao reiniciar, permite redimensionar os widgets e adiciona um widget de Pok&eacute;mon aleat&oacute;rio, tamb&eacute;m permite assistir Pok&eacute;mon em tela cheia pelo DroiD&eacute;x News.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Vers&atilde;o 5.5.7:</b> Lan&ccedil;ada em 09 de julho de 2014 - Adiciona op&ccedil;&atilde;o de abrir no Smogon, um novo menu lateral nos smartphones para deixar o aplicativo mais organizado, adiciona a Fancy Vivillon e corrige bugs. <i>Vers&atilde;o 5.5.7.1</i> lan&ccedil;ada dia 11 de julho de 2014 adiciona o Mega Metagross.</p>\n" +
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
	private final static String aboutpt = "" +
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
		"<p style=\"text-align: justify;\">As principais diferen&ccedil;as se comparado ao D&eacute;xDroid s&atilde;o uma p&aacute;gina de configura&ccedil;&otilde;es para mudar a cor do aplicativo, poder adicionar widgets de QUALQUER Pok&eacute;mon na tela inicial, sons dos Pok&eacute;mon no anime e jogo e gifs animados do Pok&eacute;mon XY de TODOS os Pok&eacute;mon.</p>\n" +
		"<h3 style=\"text-align: justify;\">Licen&ccedil;a</h3>\n" +
		"<p style=\"text-align: justify;\">Esse aplicativo foi lan&ccedil;ado sob <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=license\">licen&ccedil;a GPLv3</a> e o c&oacute;digo fonte dele est&aacute; dispon&iacute;vel no meu <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=droidexgithub\">GitHub</a>. Todo mundo esta permitido a modificar e lan&ccedil;ar esse aplicativo em seu nome, mas vai ter que liberar o c&oacute;digo fonte. E, se voc&ecirc; fizer isso, por favor d&ecirc; os devidos cr&eacute;ditos a mim, <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=alefegithub\">Alefe Souza</a>, e ao desenvolvedor do D&eacute;xDroid, <a href=\"http://aloogle.tumblr.com/droidex/ redirects?redirect=radhigithub\">Radhi Fadlillah</a>.</p>\n" +
		"<h3 style=\"text-align: justify;\">Fontes</h3>\n" +
		"<p style=\"text-align: justify;\">Base de dados foi tirada de Veekun's git (<a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun1\">aqui</a> ou <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun2\">aqui</a>). Foi apenas convertido de CSV para SQLITE.</p>\n" +
		"<p style=\"text-align: justify;\">Imagens e sprites foram tiradas de:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Veekun's <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun3\">git</a> e seu <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun4\">site</a>.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=bulbapedia\">Bulbapedia</a>, a comunidade dirigida a enciclop&eacute;dia Pok&eacute;mon.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=legendarypokemon\">LegendaryPokemon</a>, por algumas Sugimori Art.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=serebii\">Serebii</a>, por alguns sprites do Pok&eacute;mon X/Y e Mega Evolu&ccedil;&otilde;es.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=pkparaiso\">PkPara&iacute;so</a>, por todos os sprites da sexta gera&ccedil;&atilde;o.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=deviantart\">DeviantArt</a>, pelas Sugimori Art e sprites dos Pok&eacute;mons recentemente anunciados e n&atilde;o anunciados oficialmente.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\">Sons do anime e jogo foram tirados de:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=pokemon3d\">F&oacute;rum Pok&eacute;mon 3D</a>, por todos os sons no anime.</li>\n" +
		"<li><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=veekun4\">Veekun</a>, por todos os sons no jogo.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Agradecimentos</h3>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=radhigithub\">Radhi Fadlillah</a>, desenvolvedor do D&eacute;xDroid.</li>\n" +
		"<p style=\"text-align: justify;\">Nesse aplicativo foi usado as seguintes biblioteca/ferramentas:</p>\n" +
		"<ul>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=sqlhelper\">Android SQLiteAssetHelper</a> e <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=systembartint\">SystemBarTint</a> bibliotecas por <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=jgilfelt\">Jeff Gilfelt</a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=holocolors\">Android Holo Colors Plugin</a> por <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=jeromevdl\"><span class=\"vcard-fullname\">J&eacute;r&ocirc;me Van Der Linden</span></a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=giflib\">Animated GIF ImageView Library for Android</a> por <a href=\"http://aloogle.tumblr.com/droidex/redirects?redirect=abhinava\"><span class=\"vcard-fullname\">Abhinava Srivastava</span></a>.</li>\n" +
		"</ul>";
	private final static String helppt = "" +
		"<p style=\"text-align: justify;\"><b>Por que as imagens n&atilde;o carregam (aparecem apenas Missingnos)?</b></p>\n" +
		"<p style=\"text-align: justify;\">As imagens n&atilde;o s&atilde;o inclu&iacute;das no aplicativo para deixa-lo mais leve, mas voc&ecirc; pode baixa-las indo em Menu (os tr&ecirc;s quadradinhos no canto superior direito se seu celular n&atilde;o tiver bot&otilde;es, e o bot&atilde;o a esquerda do bot&atilde;o central se tiver) e tocando em \"Baixar imagens\".</p>\n" +
		"<p style=\"text-align: justify;\"><b>Apareceu \"Sugimori art\" e \"Sprite\", mas qual eu devo baixar e qual a diferen&ccedil;a entre os dois?</b></p>\n" +
		"<p style=\"text-align: justify;\">Sprites s&atilde;o as imagens pequenas e Sugimori art as grandes, por isso elas s&atilde;o bem mais pesadas, entenda que se voc&ecirc; baixar s&oacute; os sprites as imagens grandes n&atilde;o v&atilde;o aparecer, assim como se voc&ecirc; baixar s&oacute; as Sugimori art as imagens pequenas n&atilde;o v&atilde;o aparecer, se voc&ecirc; quiser tudo funcionando direitinho, baixe as duas.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Por que n&atilde;o devo fechar o aplicativo durante o download?</b></p>\n" +
		"<p style=\"text-align: justify;\">O aplicativo baixa as imagens em um .zip e as extrai ao fim do download, e n&atilde;o tem como um aplicativo fazer algo ao final do download se estiver fechado (a n&atilde;o ser que seja aqueles aplicativos chatos que voc&ecirc; nunca abre e mesmo for&ccedil;ando parada voltam do al&eacute;m pra rodar em segundo plano). As vezes o Android fecha os aplicativos minimizados, isso &eacute; do pr&oacute;prio sistema, ent&atilde;o se poss&iacute;vel nem sequer minimize-o durante o download.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Baixei tudo mas ainda aparecem Missingnos</b></p>\n" +
		"<p style=\"text-align: justify;\">Normalmente eles aparecem em Pok&eacute;mons recentes porque ainda n&atilde;o tem sprite oficial.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Por que os gifs demoram muito para carregarem?</b></p>\n" +
		"<p style=\"text-align: justify;\">Gifs animados s&atilde;o pesados, n&atilde;o espere que uma internet de operadora ap&oacute;s o limite da franquia carregue algo, carregue preferencialmente com wi-fi.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Por que n&atilde;o tem op&ccedil;&atilde;o de baixar os gifs?</b></p>\n" +
		"<p style=\"text-align: justify;\">Como j&aacute; disse, gifs animados s&atilde;o pesados, se fosse para baixar todos daria mais ou menos 1GB.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Como adiciono Pok&eacute;mons na minha tela inicial?</b></p>\n" +
		"<p style=\"text-align: justify;\">Primeiro baixe as Sugimori art, depois v&aacute; no menu de todos os aplicativos do seu dispositivo e toque em Widgets, depois pressione \"Adicionar Pok&eacute;mon\" (caso n&atilde;o haja Widgets nesse menu, pressione uma parte vazia da sua tela inicial por um segundo e toque em Widgets).</p>\n" +
		"<p style=\"text-align: justify;\"><b>Porque os sons dos Pok&eacute;mon demoram pra tocar ou as vezes nem tocam?</b></p>" +
		"<p style=\"text-align: justify;\">O aplicativo precisa baixar o &aacute;udio pra depois toca-lo, o que pode demorar uns segundos dependendo da sua velocidade de internet, se voc&ecirc; quiser que toque imediatamente e sem conex&atilde;o com a internet, baixe todos os sons em Menu > Baixar sons, e quando nem aparece &eacute; porque o Pok&eacute;mon n&atilde;o tem &aacute;udio no aplicativo ainda</p>" +
		"<p style=\"text-align: justify;\"><b>O aplicativo est&aacute; em ingl&ecirc;s?????</b></p>\n" +
		"<p style=\"text-align: justify;\">Leva muuuuuito tempo para traduzir todas as descri&ccedil;&otilde;es de Pok&eacute;mons, movimentos, habilidades, etc da primeira a sexta gera&ccedil;&atilde;o, mas alguns usu&aacute;rios do DroiD&eacute;x j&aacute; est&atilde;o trabalhando nisso,  uma vers&atilde;o totalmente em portugu&ecirc;s sair&aacute; em breve.</p>\n";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changelog_about_help);
		getWindow().setBackgroundDrawable(null);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Other.ActionBarColor(this);
		Other.ActionBarIcon(this);
		Other.SystemBarColor(this, true);

		TextView txtOK = (TextView)findViewById(R.id.txtOkChngAbout);
		WebView viewChangelog = (WebView)findViewById(R.id.viewAboutChangelog);

		int AboutOrChange = getIntent().getIntExtra(Other.WebViewValue, 0);

		if (AboutOrChange == 0) {
			Other.ActionBarColorIcons(this, "Changelog");
			viewChangelog.loadData(changelog, "text/html", "UTF-8");
		} else if (AboutOrChange == 1) {
			Other.ActionBarColorIcons(this, getString(R.string.about));
			viewChangelog.loadData(about, "text/html", "UTF-8");
		} else if (AboutOrChange == 2) {
			Other.ActionBarColorIcons(this, getString(R.string.help));
			viewChangelog.loadData(help, "text/html", "UTF-8");
		} else if (AboutOrChange == 3) {
			Other.ActionBarColorIcons(this, "Changelog");
			viewChangelog.loadData(changelogpt, "text/html", "UTF-8");
		} else if (AboutOrChange == 4) {
			Other.ActionBarColorIcons(this, getString(R.string.about));
			viewChangelog.loadData(aboutpt, "text/html", "UTF-8");
		} else if (AboutOrChange == 5) {
			Other.ActionBarColorIcons(this, getString(R.string.help));
			viewChangelog.loadData(helppt, "text/html", "UTF-8");
		}

		txtOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ActivityAboutChangelogHelp.this.finish();
			return true;
		default:
			return
			super.onOptionsItemSelected(item);
		}
	}
}