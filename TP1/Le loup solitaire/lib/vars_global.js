/**
	menu : onglet du menu
	kais: toutes les compétences de kais
	equipments: equipement de départ (nécessaire pour vérifier si le joueur ne triche pas lors de nouvelle partie)
	weapons: toutes les armes du jeu (à compléter)
	spe_object: tous les objets spécials du jeu (à compléter)
	backpack: tous les objets pouvant aller dans la sac à dos (à compléter)
**/

module.exports = {
	menu : { 'Accueil': '/', 'Nouvelle partie': '/new_game', 'Chapitre': '/chap', 'Guide de jeu': '/help' },
	kais : {"CAMOUFLAGE" : "Camouflage", "HUNTING" : "Chasse", "SENSE" : "Sixème sens", "TRAKING" : "Orientation", "HEALING" : "Guérison", "WEAPONSKILL" : "Maîtrise d'arme  ", "MINDSHIELD" : "Bouclier psychique", "MINDBLAST" : "Puissance psychique", "ANIMALKINSHIP" : "Communication animale", "MINDOVERMATTER" : "Maîtrise psychique de la matière"},  
	equipments : {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton", "WASTCOST" : "Gilet de cuir matelassé", "POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"},
	weapons : {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton"},
	spe_object : {"WASTCOST" : "Gilet de cuir matelassé"},
	backpack : {"POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"}
}


