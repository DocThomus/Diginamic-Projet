package fr.durandal.durandalback.product;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.durandal.durandalback.DatabaseHelper;

@RestController
public class ProductDAO {

	@GetMapping(value="/produit", produces= MediaType.APPLICATION_JSON_VALUE)
	public  Product getProductDetails( @RequestParam(value="id" ) Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();

		Product p = em.find(Product.class, id);
		System.out.println(p.getDescription());
		return p;
	}

	@RequestMapping(value = "/produit", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.CREATED)
	public String addProduct(@RequestBody Product p) {

		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(p);
		DatabaseHelper.commitTxAndClose(em);
		return "Produit Ajouté";

	}


	@PutMapping(value = "/produit" , consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.ACCEPTED)
	public String updateProduct(@RequestBody Product prod) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.merge(prod);
		DatabaseHelper.commitTxAndClose(em);

		return "Produit a jour";

	}

	@DeleteMapping(value = "/produit")
	@ResponseStatus( HttpStatus.ACCEPTED)
	public String delProduct(@RequestBody long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		Product prod = em.find(Product.class, id);
		em.remove(prod);
		DatabaseHelper.commitTxAndClose(em);

		return "Produit Supprimé";

	}

	public static void initProducts() {
		ArrayList<Product> tableauProduits = new ArrayList<Product>();
		
		String s1 = "Tout le monde sait jouer au 1000 Bornes : la première équipe qui franchit 1000 km - 1000 Bornes, en parant intelligemment les embûches de ses adversaires, gagne la partie! (Joueurs: 2 à 8, Date: 1954)";
		String s2 = "Lancez-vous dans une course jusqu’à 6 joueurs en simultané et soyez le plus rapide à copier le code en superposant vos cartes transparentes. Dans Copyright, il y a 66 cartes transparentes comportant chacune une combinaison unique de 3 symboles. Tous les joueurs jouent en même temps : en superposant vos cartes, vous devrez être le premier à recopier un modèle imposé (qui change aléatoirement à chaque partie). (Joueurs: 2 à 6, Date: 2009)";
		String s3 = "Trouvez le point commun entre les mots ! Illico se joue par équipes, et la partie est arbitrée par un sablier. Quel est le lien entre un fruit et une explosion ? Serait-ce la grenade ? Un couple et le nettoyage ? Mmh… le ménage non ? Cogitez aussi vite que possible car le sablier s’écoule ! Si une équipe trouve la solution, elle retourne le sablier et c'est au tour de l'autre équipe...avec le temps qui reste.Si une équipe ne trouve pas la solution avant épuisement du sablier, elle perd la manche. La partie se termine lorsqu’une équipe a remporté trois manches. (Joueurs: 2 à 8, Date: 2012)";
		String s4 = "Vous aimez les chats ? Vous aimez les cubes ? Pourquoi hésitez-vous encore ?! Le rubik's cube 2x2 designé chat. (Joueurs: 1, Date: 2016)";	
		String s5 = "Devenez adepte du Mölkky ce jeu de plein air, jeu de lancer très populaire en Scandinavie qui combine adresse, tactique et chance et s’apparente à la fois au jeu de pétanque, de billard et de bowling. C’est très simple, et très convivial, il s’agit d’être le premier à atteindre 50 points très exactement en renversant les quilles. Mais ne dépassez pas 50 points sinon votre score redescendra à 25 ! ((Joueurs: 2 à 6, Date: 2015)";
		String s6 =	"Faut-il encore présenter le Rubik's Cube ? But du jeu : remettre dans l’ordre les faces du cube après les avoir mélangées. Le tout dans un minimum de temps (record du monde : 9s). Le casse-tête contient 43 milliards de milliards de combinaisons. Un livret conseil est fourni. (Joueurs: 1, Date: 2012)";
		String s7 = "Dans Fallout, 1 à 4 joueurs découvrent les paysages irradiés inspirés par ceux de Fallout 3, Fallout 4 et leurs extensions téléchargeables. Avec quelques zones spécifiques repérées sur leur carte, les joueurs peuvent choisir de travailler ensemble ou en solo pour explorer ces étranges contrées qui s’ouvrent à eux. Au cours de l’aventure, les joueurs auront une large variété de choix comme courir après l’expérience ou les caps, rassembler les items ou les compagnons loyaux, ou encore poursuivre la quête principale ou la laisser de coté pour explorer les abris. (Joueurs: 1 à 4, Date: 2015)";
		String s8 = "Le roi Arthur cherche un héritier digne de monter sur le trône ! Avec l’aide de Merlin, il s’efforce donc de trouver le candidat idéal parmi les Chevaliers de la Table Ronde.Chaque joueur incarne l’un d’entre eux et s’efforce de gagner la faveur d’Arthur pour devenir le nouveau roi. Les dés permettent à chaque joueur de déplacer Merlin ou son propre chevalier sur la piste d’action circulaire. La case où ils terminent leur déplacement indique l’action possible. Chaque joueur contrôle le déplacement de son propre chevalier, tandis que Merlin est déplacé par tous les joueurs.(Joueurs: 2 à 4, Date: 2017)";
		String s9 =	"Paris, fin du 14 eme siècle. Complotez à l'ombre de la légendaire cathédrale de Notre-Dame afin d'assurer votre suprématie sur la ville. Utilisez habilement vos cartes, corrompez les bons personnages et prenez garde à la peste pour remporter richesse et prestige. Un fabuleux jeu de stratégie proposant de nombreuses possibilités de remporter la victoire. (Joueurs: 2 à 5, Date: 2007)";
		String s10 = "Un meurtre... six suspects... Entrez dans le plus célèbre des jeux de déduction! Le célèbre milliardaire Samuel Lenoir a été assassiné... et c'est à vous de résoudre le mystère ! Elaborez vos propres hypothèses en posant des questions à vos adversaires. Qui l'a tué ? Dans quelle pièce ? Et avec quelle arme ? Pour mettre à rude épreuve son sens de la déduction, en toute convivialité. (Joueurs: 2 à 6, Date: 1949)";
		String s11 = "Vous aimez faire des folies avec votre argent ? Laissez-vous aller à des achats frénétiques de propriétés grâce à cette version classique du MONOPOLY ! Achetez des rues, des maisons et des hôtels et devenez le magnat de l'immobilier dont vous avez toujours rêvé. Négociez et marchandez votre course vers le sommet. Vous allez posséder cette ville en un rien de temps ! Faites le choix d'acheter les propriétés les plus recherchées et les plus chères du plateau et regardez vos profits décoller. (Joueurs: 2 à 6, Date: 1935)";
		String s12 = "Partez à la conquête du monde et envahissez les pays de vos adversaires ! Cette version au graphisme renouvelé propose un accès simplifié aux règles basiques, ainsi qu’une explication pour mise en place rapide. Les éléments de jeu ont également été revus pour plus de réalisme dans les combats : les pions en 3 dimensions sont directement inspirés de l’ère napoléonienne. (Joueurs: 2 à 5, Date: 2007)";
		String s13 = "Les échecs et les dames font vibrer les joueurs du monde entier depuis des siècles. Introduit dans le sud de l’Europe au Xe siècle, le jeu d’échecs dériverait d’un jeu indien datant du VIe siècle. Pratiqués par des millions de joueurs de par le monde, les échecs font l’objet de tournois internationaux et sont considérés comme le plus noble des jeux de réflexion. (Joueurs: 2 à 2, Date: 2017)";
		String s14 = "60 000 jeux vendus par an en France ! Découvrez 40 défis de niveau progressif, pour petits et grands. L’objectif est toujours le même : sortir la voiture rouge d’un gros embouteillage en faisant glisser sur le plateau les véhicules qui la bloquent. À vos méninges, prêts, partez ! Un jeu à notoriété forte, en France comme aux États-Unis. Fer de lance d’une gamme de jeux enfants amusants et intelligents. Logique, réflexion et déduction pour tous les âges ! (Joueurs: 1, Date: 2015)";
		String s15 = "Plus besoin d'une intelligence artificielle pour jouer à Tetris Link ! Votre jeu vidéo préféré a enfin pris vie pour devenir un jeu de société pour toute la famille ! Pour marquer des points, empilez des Tetriminos de même couleur tout en empêchant vos adversaires de le faire ! (Joueurs: 2 à 4, Date: 2012)";
		String s16 = "Vous faites partie de Forces Spéciales de l'Agence Spatio-Temporelle Y ! Partez en mission accompagnés de Dooz, votre fidèle assistant robot... mais attention : il ne peut maintenir le portail temporel ouvert qu'une 60aine de minutes ! Pour cette mission, voyagez en l'an 2394 afin de contrer les plans du cruel Snarf, un savant fou qui compte paralyser les ordinateurs du monde entier grâce à un puissant virus informatique ! (Joueurs: , Date: 2015)";
		String s17 = "Toutes les règles et les conseils pour mener une partie de main de maître. Le Guide du Maître est l’ouvrage de référence pour les maîtres de donjon. Il contient un nombre incroyable de conseils et d’astuces pour arbitrer les règles, gérer les combats, distribuer les trésors et les points d’expérience et mettre en scène des quêtes passionnantes dans l'univers de Dungeons&Dragons 4. Fruit de nombreux mois de tests, écrit par des experts du jeu de rôle, le Guide du Maître est un outil précieux, pour les MD débutants ou vétérans. (Joueurs: , Date: 2008)";
		String s18 = "Smaug a été vaincu, la bataille des Cinq Armées a été gagnée, et Bilbo est rentré à La Comté. La Guerre de l’Anneau est pour dans quelques générations. Dans la paix relative, les peuples libres des Terres Sauvages regardent au-delà de leurs frontières pour la première fois, établissant des routes marchandes, renouvelant les liens entre leurs cultures et apportant la prospérité dans la région au nord de la Forêt Noire, du Mont Solitaire, et sur le versant est des Monts Brumeux. (Joueurs: , Date: 2008)";
		String s19 = "Ce jeu s’inspire de ce qui se déroula en Europe de l’ouest en 1944, quelques mois après le débarquement. Vous allez incarner le rôle d’un des trois principaux ‭commandants alliés qui se sont livrés à une véritable course pour atteindre le Rhin ‭dans l’idée de mettre fin à la Seconde Guerre Mondiale avant Noël… Celui qui ‭traversera le fleuve en premier gagnera la partie. Mais le temps est compté. Les défenses de l’Axe vont devenir de plus en plus redoutables au fur et à mesure que ‭vous allez vous rapprocher de l’Allemagne. (Joueurs: 1 à 3, Date: 2017)";
		String s20 = "Britannia, le classique des jeux de conquête est de retour ! Prenez en main le destin des 17 civilisations se battant pour le contrôle de la Grande-Bretagne au cours des siècles. Rassemblez vos nations, écrasez vos ennemis et bâtissez votre Empire. Une stratégie élaborée et l’histoire elle-même joueront un rôle dans cette bataille épique pour la domination politique et territoriale. Cette nouvelle édition de Britannia comporte de nouveaux graphismes, de nouveaux éléments de jeux, et de nouvelles règles, pour un jeu plus... (Joueurs: 3 à 5, Date: 2012)";
		String s21 = "878 - Les Vikings est le premier opus de la série Birth of Europe, cousine de la série Birth of America (1775 La Révolution Américaine, 1812 L’Invasion de Canada, 1754 La Conquête). Il a pour thème les invasions vikings sur les côtes anglo-saxonnes au IXème siècle. (Joueurs: 2 à 4, Date: 2015)";
		String s22 = "Nous sommes en 1775. Outragées par les nouvelles taxes que leur impose la Grande Bretagne, les colonies américaines ont commencé à stocker des armes et à organiser des milices. Le 18 avril, des miliciens tendent une embuscade à une colonnes de 700 “Chemises rouges” anglais envoyés pour se saisir des stocks d’armes. 273 soldats anglais sont tués ou blessés avant que la colonne n’atteigne Boston. La Révolution Américaine a commencé ! Chemises rouges anglais, Loyalistes anglais, Hessois, Réguliers américains, Patriotes, Français et Indiens sont sous vos ordres pour décider du sort des Amériques. Les joueurs de chaque camp coopèrent pour prendre le contrôle des forts et villes stratégiques. (Joueurs: 2 à 4, Date: 2010)";
		String s23 = "Dès la fondation des Etats-Unis d’Amérique, l’esclavage et tout ce qu’il incarnait a été un point de discorde. Grâce aux efforts d’hommes et de femmes à travers tous les états, il a été aboli. C’est cette partie de l’histoire que Freedom- The Underground Railroad vous propose de rejouer. Dans ce jeu, les joueurs incarneront des abolitionnistes. Ils devront tenter de mettre fin à l’esclavage en usant par exemple de leur influence lors d’événements clés, en levant des fonds pour leur cause ou encore en organisant la fuite d’esclaves vers des provinces canadienne (Joueurs: 1 à 4, Date: 2012)";
		String s24 = "Res Publica Romana est un jeu concentrant 250 ans d’histoire politique durant la République de Rome, de la 1ère Guerre Punique (264 avant J.C.) jusqu’à l’assassinat de Jules César en 44 avant J.C. Les joueurs contrôlent des factions composées de familles importantes du Sénat et s’affrontent pour obtenir des charges, des commandements militaires, des concessions économiques et de nouveaux partisans. (Joueurs: 1 à 6, Date: 2008)";
		String s25 = "Soyez le plus rapide pour l’emporter dans l’épreuve « Chrono » puis défiez les autres joueurs grâce aux nouveaux dés de force, vitesse et camouflage. Saurez-vous mener vos animaux à la victoire et tenir le rythme de « Défis Nature Chrono » ? (Joueurs: 2 à 4, Date: 2015)";
		String s26 = "Améliorez votre connaissance des pays grâce aux questions/réponses portées sur 200 cartes à jouer de qualité supérieure et aux données mentionnées sur le planisphère. Chaque carte comprend des informations concernant la situation géographique, la superficie, la capitale et le nombre d’habitants des pays… l’Europe, ainsi que des renseignements sur leurs montagnes, leurs frontières, leurs lacs…. Drapeaux du Monde standard comporte une version simplifiée pour les plus jeunes joueurs (dès 6/8 ans) et également plusieurs variantes permettant d’ augmenter le degré de difficulté du jeu selon l’âge et le niveau de connaissances des participants. (Joueurs: 2 à 9, Date: 2017)";
		String s27 = "Si vous pensiez bien connaître l’histoire de Paris et de ses monuments, vous risquez d’être surpris ! Maxitour Paris offre une vison de notre capitale aussi nouvelle en terme de jeu qu’intéressante en terme de connaissances. Une belle occasion de voyager sur un plateau, et de découvrir ou de redécouvrir la ville lumière. (Joueurs: 2 à 6, Date: 2013)";
		String s28 = "Vite, vite ! Aide les 4 petites souris à regagner leur lit avant que le chat n’ait mangé les 5 poissons ! (Joueurs: 2 à 4, Date: 2012)";
		String s29 = "Les grenouilles balancent ! De petites grenouilles jouent en équilibre sur des nénuphars. Attention à ce qu'elles ne tombent pas dans la mare. (Joueurs: 2 à 8, Date: 2005)";
		String s30 = "Les animaux du fermier Pippo sont tous uniques : il y a des chevaux, des cochons, des chats, des chiens et des vaches, tous combinés en 5 couleurs différentes. A chaque tour on retourne une carte qui présente 4 animaux dans 4 couleurs. Vite, vite, il faut chercher l'animal manquant dans la bonne couleur. Le premier qui le trouve gagne la carte. Quand toutes les cartes ont été trouvées, celui qui en a le plus a gagné. Un jeu intelligent qui aiguise les réflexes et le sens de l'observation. (Joueurs: 2 à 8, Date: 2005)";
		
		String uri = "https://placeimg.com/240/280";
		
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		Product p1 = new Product("1000 bornes",s1,19.90,35,"Cartes et petits jeux", "https://placeimg.com/240/280");
		Product p2 = new Product("Copyright",s2,10.90,22,"Cartes et petits jeux", "https://placeimg.com/240/280");
		Product p3 = new Product("Illico",s3,11.50,36,"Cartes et petits jeux", "https://placeimg.com/240/280");
		Product p4 = new Product("Cat cube",s4,10.90,60,"Casse tête", "https://placeimg.com/240/280");
		Product p5 = new Product("Molkky original",s5,29.90,16,"Casse tête", "https://placeimg.com/240/280");
		Product p6 = new Product("Rubik's cube",s6,16.90,41,"Casse tête", "https://placeimg.com/240/280");
		Product p7 = new Product("Fallout",s7,53.90,29,"Jeux de plateau expert", "https://placeimg.com/240/280");
		Product p8 = new Product("Merlin",s8,49.90,25,"Jeux de plateau expert", "https://placeimg.com/240/280");
		Product p9 = new Product("Notre dame",s9,31.50,18,"Jeux de plateau expert", "https://placeimg.com/240/280");
		Product p10 = new Product("Cluedo",s10,29.90,3,"Jeux de plateau famille", "https://placeimg.com/240/280");
		Product p11 = new Product("Monopoly",s11,13.90,19,"Jeux de plateau famille", "https://placeimg.com/240/280");
		Product p12 = new Product("Risk",s12,37.90,16,"Jeux de plateau famille", "https://placeimg.com/240/280");
		Product p13 = new Product("Jeu d'échecs et de dames",s13,14.50,30,"Jeux de reflexion", "https://placeimg.com/240/280");
		Product p14 = new Product("Rush Hour",s14,21.90,12,"Jeux de reflexion", "https://placeimg.com/240/280");
		Product p15 = new Product("Tetris Link",s15,21.90,15,"Jeux de reflexion", "https://placeimg.com/240/280");
		Product p16 = new Product("Escape Game Junior",s16,7.90,12,"Jeux de rôle", "https://placeimg.com/240/280");
		Product p17 = new Product("Donjons et dragons guide du maître",s17,49.90,7,"Jeux de rôle", "https://placeimg.com/240/280");
		Product p18 = new Product("L'Anneau Unique",s18,49.95,25,"Jeux de rôle", "https://placeimg.com/240/280");
		Product p19 = new Product("1944 Race to the Rhine",s19,48.90,8,"Jeux de wargame", "https://placeimg.com/240/280");
		Product p20 = new Product("Britannia",s20,37.90,22,"Jeux de wargame", "https://placeimg.com/240/280");
		Product p21 = new Product("Les Vikings",s21,53.90,15,"Jeux de wargame", "https://placeimg.com/240/280");
		Product p22 = new Product("La révolution américiane",s22,42.90,23,"Jeux d'histoire", "https://placeimg.com/240/280");
		Product p23 = new Product("Le chemin de fer clandestin",s23,52.90,18,"Jeux d'histoire", "https://placeimg.com/240/280");
		Product p24 = new Product("ResPublica Romana",s24,49.90,16,"Jeux d'histoire", "https://placeimg.com/240/280");
		Product p25 = new Product("Défis nature chrono",s25,18.90,25,"Jeux éducatif", "https://placeimg.com/240/280");
		Product p26 = new Product("Drapeaux du monde",s26,13.90,7,"Jeux éducatif", "https://placeimg.com/240/280");
		Product p27 = new Product("Maxitour paris",s27,29.90,10,"Jeux éducatif", "https://placeimg.com/240/280");
		Product p28 = new Product("Les Petites Souris",s28,19.90,5,"Jeux jeunes Enfants", "https://placeimg.com/240/280");
		Product p29 = new Product("Little Balancing",s29,8.90,15,"Jeux jeunes Enfants", "https://placeimg.com/240/280");
		Product p30 = new Product("Pippo",s30,8.90,15,"Jeux jeunes Enfants", "https://placeimg.com/240/280");
		
		tableauProduits.add(p1);
		tableauProduits.add(p2);
		tableauProduits.add(p3);
		tableauProduits.add(p4);
		tableauProduits.add(p5);
		tableauProduits.add(p6);
		tableauProduits.add(p7);
		tableauProduits.add(p8);
		tableauProduits.add(p9);
		tableauProduits.add(p10);
		tableauProduits.add(p11);
		tableauProduits.add(p12);
		tableauProduits.add(p13);
		tableauProduits.add(p14);
		tableauProduits.add(p15);
		tableauProduits.add(p16);
		tableauProduits.add(p17);
		tableauProduits.add(p18);
		tableauProduits.add(p19);
		tableauProduits.add(p20);
		tableauProduits.add(p21);
		tableauProduits.add(p22);
		tableauProduits.add(p23);
		tableauProduits.add(p24);
		tableauProduits.add(p25);
		tableauProduits.add(p26);
		tableauProduits.add(p27);
		tableauProduits.add(p28);
		tableauProduits.add(p29);
		tableauProduits.add(p30);
		
		for(Product p : tableauProduits) {
			em.persist(p);
		}
		DatabaseHelper.commitTxAndClose(em);

	}
}
