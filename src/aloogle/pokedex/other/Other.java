package aloogle.pokedex.other;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Html;
import android.widget.ImageView;
import java.io.File;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import aloogle.pokedex.R;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class Other {
	public static final String PokemonId = "POKEMON_ID";
	public static final String PokemonImageId = "POKEMON_IMAGE_ID";
	public static final String PokemonName = "POKEMON_NAME";
	public static final int PokemonFilterCode = 90;
	public static final String PokemonFilterGeneration = "PoKEMON_FILTER_GENERATION";
	public static final String PokemonFilterType = "PoKEMON_FILTER_TYPE";
	public static final String PokemonFilterColor = "PoKEMON_FILTER_COLOR";
	public static final String PokemonFilterBaby = "PoKEMON_FILTER_IS_BABY";
	public static final String PokemonFilterGender = "PoKEMON_FILTER_HAS_GENDER_DIFF";

	public static final String ImageLocation =
		Environment.getExternalStorageDirectory().toString() + "/DroiDex/";
	public static final String ArtURL =
		"https://github.com/alefesouza/aloogle-files/releases/download/1.0/Art.zip";
	public static final String SpritesURL =
		"https://github.com/alefesouza/aloogle-files/releases/download/1.0/Sprites.zip";
	public static final String SoundAnimeURL =
		"https://github.com/alefesouza/aloogle-files/releases/download/1.0/SoundAnime.zip";
	public static final String SoundGameURL =
		"https://github.com/alefesouza/aloogle-files/releases/download/1.0/SoundGame.zip";
	public static final String WebViewValue = "WEB_VIEW_VALUE";

	public static final String PokemonColorBlack = "143|197|198|201|215|228|229|303|325|336|344|353|354|355|356|430|441|446|461|477|487|491|522|523|561|562|608|609|644|664|665|666|10000|10001|10002|10003|10004|10005|10006|10007|10008|10009|10010|10011|10012|10013|10014|10015|10016|10017|10018|10019|10020|10021|10022|10023|10024|10025|10026|10027|10063|10082|10086|10087|10088|10089|10090|10091|10092|10093|10094|10095|10096|10097|10098|10099|10100|10101|10102|10134|10148|10152|10156|10161|10171";
	public static final String PokemonColorBlue = "7|8|9|29|30|31|43|44|55|60|61|62|72|73|114|116|117|130|131|134|138|139|144|147|148|158|159|160|170|171|183|184|189|194|195|202|214|230|231|245|258|259|260|276|277|283|284|294|295|298|307|319|320|321|333|334|340|358|360|363|364|365|366|367|371|373|374|375|376|378|381|382|393|394|395|403|404|405|408|409|443|444|445|447|448|453|454|456|457|458|465|471|482|489|490|501|502|503|515|516|524|525|526|527|528|535|536|537|539|564|565|580|588|603|604|605|615|633|634|635|638|642|656|657|658|686|687|692|693|698|699|712|713|716|10029|10039|10040|10041|10067|10080|10132|10136|10141|10147|10158|10159|10166|10168|10172|10178|10179|10183";
	public static final String PokemonColorBrown = "13|16|17|18|20|21|22|37|50|51|56|57|58|59|63|64|65|74|75|76|83|84|85|104|105|106|107|115|120|127|128|133|140|141|149|161|162|163|164|185|216|217|220|221|234|237|244|263|273|274|275|285|287|289|292|297|324|327|328|343|349|377|390|391|392|396|397|398|399|400|418|419|427|428|438|449|450|473|485|504|505|506|534|551|552|586|606|618|626|629|630|645|659|660|667|668|672|673|679|680|681|688|689|690|691|696|708|709|710|711|10070|10081|10125|10126|10127|10128|10129|10130|10131|10137|10139|10140|10180";
	public static final String PokemonColorGray = "66|67|68|81|82|95|111|112|200|204|208|211|223|227|232|247|261|262|290|299|304|305|306|313|339|347|348|361|362|369|379|410|411|412|413|431|432|462|464|476|493|507|508|519|520|521|529|530|532|533|544|570|571|572|573|589|597|598|599|600|601|632|639|646|677|703|707|720|10041|10042|10043|10044|10045|10046|10047|10048|10049|10050|10051|10052|10053|10054|10055|10056|10057|10085|10153";
	public static final String PokemonColorGreen = "1|2|3|10|11|69|70|71|123|152|153|154|167|177|178|182|186|188|246|248|251|252|253|254|269|270|271|272|286|309|315|316|329|330|331|332|346|352|357|384|387|388|389|406|407|436|437|455|469|470|492|495|496|497|511|512|541|546|547|548|549|550|556|568|569|577|578|579|610|611|622|623|640|641|650|651|652|701|718|10064|10066|10068|10071|10079|10149|10165|10133|10186";
	public static final String PokemonColorPink = "35|36|39|40|79|80|102|108|113|122|137|151|173|174|180|187|199|209|222|238|241|242|293|300|350|368|370|420|421|439|440|463|481|517|518|531|594|682|683|700|719|10035|10037|10038|10170|10181|10182";
	public static final String PokemonColorPurple = "19|23|24|32|33|34|41|42|48|49|88|89|90|91|92|93|94|109|110|121|132|142|150|169|190|196|205|207|210|226|236|268|301|302|314|317|326|345|422|423|424|425|426|429|434|435|442|451|452|472|484|509|510|574|575|576|620|649|704|705|706|714|715|10030|10075|10076|10077|10078|10138|10142|10143|10144|10063|10064|10163|10164|10167";
	public static final String PokemonColorRed = "4|5|6|45|46|47|98|99|100|101|118|119|124|126|129|136|165|166|168|193|212|218|219|224|225|233|240|250|255|256|257|265|308|318|323|338|341|342|380|383|386|401|402|467|474|479|498|499|500|513|514|538|543|545|553|554|555|557|558|560|616|617|621|624|625|628|631|653|654|655|661|662|663|697|717|721|10028|10031|10032|10033|10058|10059|10060|10061|10062|10069|10072|10135|10146|10150|10154|10169|10184";
	public static final String PokemonColorWhite = "12|86|87|175|176|179|235|249|264|266|278|280|281|282|288|335|351|359|372|417|459|460|468|475|478|483|486|581|582|583|584|587|590|591|592|593|602|607|613|614|627|636|637|643|648|669|670|671|674|675|676|678|684|685|10073|10074|10083|10103|10104|10105|10106|10107|10108|10109|10110|10111|10112|10113|10114|10115|10116|10117|10118|10119|10120|10121|10122|10123|10124|10151|10157|10160|10162|10185";
	public static final String PokemonColorYellow = "14|15|25|26|27|28|38|52|53|54|77|78|96|97|103|125|135|145|146|155|156|157|172|181|191|192|203|206|213|239|243|267|279|291|296|310|311|312|322|337|385|414|415|416|433|466|480|488|494|540|542|559|563|566|567|585|595|596|612|619|647|694|695|702|10034|10036|10065|10084|10145|10155|10173|10174|10175|10176|10177";

	public static void setImage(ImageView img, String imgLocation, int defaultImg) {
		String location = Environment.getExternalStorageDirectory().toString() +
			"/DroiDex/" + imgLocation;
		Bitmap bitmap = BitmapFactory.decodeFile(location);

		if (bitmap == null)
			img.setImageResource(defaultImg);
		else
			img.setImageBitmap(bitmap);
	}

	public static void setImage(ImageView img, Bitmap image, int defaultImg) {
		if (image == null)
			img.setImageResource(defaultImg);
		else
			img.setImageBitmap(image);
	}

	public static int getTypeColor(int type_id) {
		switch (type_id) {
		case 1:
			return Color.parseColor("#999966");
		case 2:
			return Color.parseColor("#993333");
		case 3:
			return Color.parseColor("#9999cc");
		case 4:
			return Color.parseColor("#993399");
		case 5:
			return Color.parseColor("#cccc66");
		case 6:
			return Color.parseColor("#cc9933");
		case 7:
			return Color.parseColor("#99cc33");
		case 8:
			return Color.parseColor("#666699");
		case 9:
			return Color.parseColor("#b8b8d0");
		case 10:
			return Color.parseColor("#ff6600");
		case 11:
			return Color.parseColor("#6699ff");
		case 12:
			return Color.parseColor("#66cc66");
		case 13:
			return Color.parseColor("#ffcc33");
		case 14:
			return Color.parseColor("#ff3366");
		case 15:
			return Color.parseColor("#66cccc");
		case 16:
			return Color.parseColor("#8c33ff");
		case 17:
			return Color.parseColor("#41332a");
		case 18:
			return Color.parseColor("#ff9999");
		default:
			return 0;
		}
	}

	public static int getTypeImage(int type_id) {
		switch (type_id) {
		case 1:
			return R.drawable.type_normal;
		case 2:
			return R.drawable.type_fighting;
		case 3:
			return R.drawable.type_flying;
		case 4:
			return R.drawable.type_poison;
		case 5:
			return R.drawable.type_ground;
		case 6:
			return R.drawable.type_rock;
		case 7:
			return R.drawable.type_bug;
		case 8:
			return R.drawable.type_ghost;
		case 9:
			return R.drawable.type_steel;
		case 10:
			return R.drawable.type_fire;
		case 11:
			return R.drawable.type_water;
		case 12:
			return R.drawable.type_grass;
		case 13:
			return R.drawable.type_electric;
		case 14:
			return R.drawable.type_psychic;
		case 15:
			return R.drawable.type_ice;
		case 16:
			return R.drawable.type_dragon;
		case 17:
			return R.drawable.type_dark;
		case 18:
			return R.drawable.type_fairy;
		default:
			return 0;
		}
	}

	public static int getTypeName(int type_id) {
		switch (type_id) {
		case 1:
			return R.string.text_normal;
		case 2:
			return R.string.text_fighting;
		case 3:
			return R.string.text_flying;
		case 4:
			return R.string.text_poison;
		case 5:
			return R.string.text_ground;
		case 6:
			return R.string.text_rock;
		case 7:
			return R.string.text_bug;
		case 8:
			return R.string.text_ghost;
		case 9:
			return R.string.text_steel;
		case 10:
			return R.string.text_fire;
		case 11:
			return R.string.text_water;
		case 12:
			return R.string.text_grass;
		case 13:
			return R.string.text_electric;
		case 14:
			return R.string.text_psychic;
		case 15:
			return R.string.text_ice;
		case 16:
			return R.string.text_dragon;
		case 17:
			return R.string.text_dark;
		case 18:
			return R.string.text_fairy;
		default:
			return 0;
		}
	}

	public static void ActionBarColor(Activity activity) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		String userColor = preferences.getString("prefColor", "droidexblue");
		if (userColor.equals("red")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffcc0000));
		} else if (userColor.equals("green")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00cc00));
		} else if (userColor.equals("blue")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0000cc));
		} else if (userColor.equals("yellow")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe5e500));
		} else if (userColor.equals("gold")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffdaa520));
		} else if (userColor.equals("silver")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffc0c0c0));
		} else if (userColor.equals("crystal")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffa1e2ff));
		} else if (userColor.equals("ruby")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe0115f));
		} else if (userColor.equals("sapphire")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0f52ba));
		} else if (userColor.equals("emerald")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff50c878));
		} else if (userColor.equals("diamond")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffb9f2ff));
		} else if (userColor.equals("pearl")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffeae0c8));
		} else if (userColor.equals("platinum")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe5e4e2));
		} else if (userColor.equals("black")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000));
		} else if (userColor.equals("white")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffffffff));
		} else if (userColor.equals("droidexblue")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0080ff));
		} else if (userColor.equals("dexdroidred")) {
			activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff4444));
		}
	}

	public static void ActionBarColorIcons(Activity activity, String title) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		String userColorIcons = preferences.getString("prefColorIcons", "white");
		if (userColorIcons.equals("black")) {
			activity.getActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + title + "</font>"));
			activity.setTheme(R.style.Overflow_Black);
		} else {
			activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
		}
	}

	public static void ActionBarIcon(Activity activity) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		String userIcon = preferences.getString("prefIcon", "default");
		if (userIcon.equals("default"))
			activity.getActionBar().setIcon(R.drawable.ic_launcher);
		else if (userIcon.equals("red"))
			activity.getActionBar().setIcon(R.drawable.ic_pokedex);
		else if (userIcon.equals("green"))
			activity.getActionBar().setIcon(R.drawable.ic_abilitydex);
		else if (userIcon.equals("blue"))
			activity.getActionBar().setIcon(R.drawable.ic_itemdex);
		else if (userIcon.equals("yellow"))
			activity.getActionBar().setIcon(R.drawable.ic_movedex);
	}

	public static void ActionBarDetailsColor(Activity activity, String pokemon_image_id, String title) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		boolean prefDetailsColor = preferences.getBoolean("prefDetailsColor", true);
		if (prefDetailsColor) {
			if (pokemon_image_id.matches(PokemonColorBlack)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorBlue)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0000cc));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorBrown)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff6B4226));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorGray)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffd3d3d3));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorGreen)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00cc00));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorPink)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff69b4));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorPurple)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xff7f00ff));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorRed)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffcc0000));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorWhite)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffffffff));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + title + "</font>"));
			} else if (pokemon_image_id.matches(PokemonColorYellow)) {
				activity.getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe5e500));
				activity.getActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + title + "</font>"));
			}
		}
	}

	public static void SystemBarColor(Activity activity, boolean nav) {
		if (Build.VERSION.SDK_INT >= 19) {
			boolean tabletSize = activity.getResources().getBoolean(R.bool.isTablet);
			if (tabletSize) {}
			else {
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
				boolean prefSystemBarColor = preferences.getBoolean("prefSystemBarColor", true);
				if (prefSystemBarColor) {
					SystemBarTintManager tintManager = new SystemBarTintManager(activity);
					tintManager.setStatusBarTintEnabled(true);
					if (nav) {
						tintManager.setNavigationBarTintEnabled(true);
					} else {
						tintManager.setNavigationBarTintEnabled(false);
					}
					setTranslucentStatus(true, activity, nav);
					SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
					activity.findViewById(android.R.id.content).setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
					String userColor = preferences.getString("prefColor", "droidexblue");
					if (userColor.equals("red")) {
						tintManager.setTintColor(Color.parseColor("#ffcc0000"));
					} else if (userColor.equals("green")) {
						tintManager.setTintColor(Color.parseColor("#ff00cc00"));
					} else if (userColor.equals("blue")) {
						tintManager.setTintColor(Color.parseColor("#ff0000cc"));
					} else if (userColor.equals("yellow")) {
						tintManager.setTintColor(Color.parseColor("#ffe5e500"));
					} else if (userColor.equals("gold")) {
						tintManager.setTintColor(Color.parseColor("#ffdaa520"));
					} else if (userColor.equals("silver")) {
						tintManager.setTintColor(Color.parseColor("#ffc0c0c0"));
					} else if (userColor.equals("crystal")) {
						tintManager.setTintColor(Color.parseColor("#ffa1e2ff"));
					} else if (userColor.equals("ruby")) {
						tintManager.setTintColor(Color.parseColor("#ffe0115f"));
					} else if (userColor.equals("sapphire")) {
						tintManager.setTintColor(Color.parseColor("#ff0f52ba"));
					} else if (userColor.equals("emerald")) {
						tintManager.setTintColor(Color.parseColor("#ff50c878"));
					} else if (userColor.equals("diamond")) {
						tintManager.setTintColor(Color.parseColor("#ffb9f2ff"));
					} else if (userColor.equals("pearl")) {
						tintManager.setTintColor(Color.parseColor("#ffeae0c8"));
					} else if (userColor.equals("platinum")) {
						tintManager.setTintColor(Color.parseColor("#ffe5e4e2"));
					} else if (userColor.equals("black")) {
						tintManager.setTintColor(Color.parseColor("#ff000000"));
					} else if (userColor.equals("white")) {
						tintManager.setTintColor(Color.parseColor("#ffffffff"));
					} else if (userColor.equals("droidexblue")) {
						tintManager.setTintColor(Color.parseColor("#ff0080ff"));
					} else if (userColor.equals("dexdroidred")) {
						tintManager.setTintColor(Color.parseColor("#ffff4444"));
					}
				} else {
					setTranslucentStatus(false, activity, false);
					SystemBarTintManager tintManager = new SystemBarTintManager(activity);
					tintManager.setStatusBarTintEnabled(false);
					tintManager.setNavigationBarTintEnabled(false);
					activity.findViewById(android.R.id.content).setPadding(0, 0, 0, 0);
				}
			}
		}
	}

	public static void SystemBarDetailsColor(Activity activity, String pokemon_image_id, boolean nav) {
		if (Build.VERSION.SDK_INT >= 19) {
			boolean tabletSize = activity.getResources().getBoolean(R.bool.isTablet);
			if (tabletSize) {}
			else {
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
				boolean prefSystemBarColor = preferences.getBoolean("prefSystemBarColor", true);
				if (prefSystemBarColor) {
					SystemBarTintManager tintManager = new SystemBarTintManager(activity);
					tintManager.setStatusBarTintEnabled(true);
					if (nav) {
						tintManager.setNavigationBarTintEnabled(true);
					} else {
						tintManager.setNavigationBarTintEnabled(false);
					}
					setTranslucentStatus(true, activity, nav);
					SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
					activity.findViewById(android.R.id.content).setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
					boolean prefDetailsColor = preferences.getBoolean("prefDetailsColor", true);
					if (prefDetailsColor) {
						if (pokemon_image_id.matches(PokemonColorBlack)) {
							tintManager.setTintColor(Color.parseColor("#ff000000"));
						} else if (pokemon_image_id.matches(PokemonColorBlue)) {
							tintManager.setTintColor(Color.parseColor("#ff0000cc"));
						} else if (pokemon_image_id.matches(PokemonColorBrown)) {
							tintManager.setTintColor(Color.parseColor("#ff6b4226"));
						} else if (pokemon_image_id.matches(PokemonColorGray)) {
							tintManager.setTintColor(Color.parseColor("#ffd3d3d3"));
						} else if (pokemon_image_id.matches(PokemonColorGreen)) {
							tintManager.setTintColor(Color.parseColor("#ff00cc00"));
						} else if (pokemon_image_id.matches(PokemonColorPink)) {
							tintManager.setTintColor(Color.parseColor("#ffff69b4"));
						} else if (pokemon_image_id.matches(PokemonColorPurple)) {
							tintManager.setTintColor(Color.parseColor("#ff7f00ff"));
						} else if (pokemon_image_id.matches(PokemonColorRed)) {
							tintManager.setTintColor(Color.parseColor("#ffcc0000"));
						} else if (pokemon_image_id.matches(PokemonColorWhite)) {
							tintManager.setTintColor(Color.parseColor("#ffffffff"));
						} else if (pokemon_image_id.matches(PokemonColorYellow)) {
							tintManager.setTintColor(Color.parseColor("#ffe5e500"));
						}
					}
				} else {
					setTranslucentStatus(false, activity, false);
					SystemBarTintManager tintManager = new SystemBarTintManager(activity);
					tintManager.setStatusBarTintEnabled(false);
					tintManager.setNavigationBarTintEnabled(false);
					activity.findViewById(android.R.id.content).setPadding(0, 0, 0, 0);
				}
			}
		}
	}

	@TargetApi(19)
	public static void setTranslucentStatus(boolean on, Activity activity, boolean nav) {
		if (Build.VERSION.SDK_INT >= 19) {
			boolean tabletSize = activity.getResources().getBoolean(R.bool.isTablet);
			if (tabletSize) {}
			else {
				Window win = activity.getWindow();
				WindowManager.LayoutParams winParams = win.getAttributes();
				final int statusbar = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
				final int navbar = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
				if (on) {
					winParams.flags |= statusbar;
					if (nav) {
						winParams.flags |= navbar;
					} else {
						winParams.flags &= ~navbar;
					}
				} else {
					winParams.flags &= ~statusbar;
					winParams.flags &= ~navbar;
				}
				win.setAttributes(winParams);
			}
		}
	}

	public interface pokemonInterface {
		void pokemonSelected(String id);
		void formSelected(String id, String img_id, String name, boolean formSwitchable);
	}

	public static long startDownload(DownloadManager mgr, String url, String title, String desc, String target) {
		File f = new File(Environment.getExternalStoragePublicDirectory("DroiDex"), target);
		if (f.exists())
			f.delete();

		Uri uri = Uri.parse(url);
		Environment.getExternalStoragePublicDirectory("DroiDex").mkdirs();

		return mgr.enqueue(new DownloadManager.Request(uri)
			.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
				DownloadManager.Request.NETWORK_MOBILE)
			.setAllowedOverRoaming(false)
			.setTitle(title)
			.setDescription(desc)
			.setDestinationInExternalPublicDir("DroiDex", target));
	}
}
