var app = angular.module('monApp',['ngCookies']);



var config = {
  headers : {
    'Content-Type': 'application/json'
  }
};

app.value('kais', {"CAMOUFLAGE" : "Camouflage", "HUNTING" : "Chasse", "SENSE" : "Sixème sens", "TRAKING" : "Orientation", "HEALING" : "Guérison", "WEAPONSKILL" : "Maîtrise d'arme  ", "MINDSHIELD" : "Bouclier psychique", "MINDBLAST" : "Puissance psychique", "ANIMALKINSHIP" : "Communication animale", "MINDOVERMATTER" : "Maîtrise psychique de la matière"});
app.value('equipments', {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton", "WASTCOST" : "Gilet de cuir matelassé", "POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"});
app.value('weapons', {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton"});
app.value('spe_object', {"WASTCOST" : "Gilet de cuir matelassé", "BAKANAL_OIL" : "Huile de Bakanal", "BONE_SWORD" : "Épée d'Os", "BLUE_STONE" : "Disque de Pierre Bleue"});
app.value('backpack', {"POTION" : "Potion de Lampur", "RATION" : "Rations spéciales", "FOURRURE" : "Fourrure", "CORDE" : "Corde"});