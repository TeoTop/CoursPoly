module.exports = {
	menu : { 'Accueil': '/', 'Nouvelle partie': '/new_game', 'Chapitre': '/chap', 'Guide de jeu': '/help' },
	kais : {"CAMOUFLAGE" : "Camouflage", "HUNTING" : "Chasse", "SENSE" : "Sixème sens", "TRAKING" : "Orientation", "HEALING" : "Guérison", "WEAPONSKILL" : "Maîtrise d'arme  ", "MINDSHIELD" : "Bouclier psychique", "MINDBLAST" : "Puissance psychique", "ANIMALKINSHIP" : "Communication animale", "MINDOVERMATTER" : "Maîtrise psychique de la matière"},  
	equipments : {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton", "WASTCOST" : "Gilet de cuir matelassé", "POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"},
	weapons : {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton"},
	spe_object : {"WASTCOST" : "Gilet de cuir matelassé"},
	backpack : {"POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"}
}

function getRandom(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}


