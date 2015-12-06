var app = angular.module('monApp',['ngCookies']);

var config = {
  headers : {
    'Content-Type': 'application/json'
  }
};

app.value('kais', {"CAMOUFLAGE" : "Camouflage", "HUNTING" : "Chasse", "SENSE" : "Sixème sens", "TRAKING" : "Orientation", "HEALING" : "Guérison", "WEAPONSKILL" : "Maîtrise d'arme  ", "MINDSHIELD" : "Bouclier psychique", "MINDBLAST" : "Puissance psychique", "ANIMALKINSHIP" : "Communication animale", "MINDOVERMATTER" : "Maîtrise psychique de la matière"});
app.value('equipments', {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton", "WASTCOST" : "Gilet de cuir matelassé", "POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"});
app.value('weapons', {"SWORD" : "Epée", "SHORTSWORD" : "Sabre", "SPEAR" : "Lance", "MACE" : "Masse d'armes", "WARHAMMER" : "Marteau de guerre", "AXE" : "Hâche", "BROADSWORD" : "Glaive", "QUATERSTAFF" : "Bâton"});
app.value('spe_object', {"WASTCOST" : "Gilet de cuir matelassé"});
app.value('backpack', {"POTION" : "Potion de Lampur", "RATION" : "Rations spéciales"});