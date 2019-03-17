

var carre;
var id_partie;
var tentatives = randomInt(5, 10);
insertCoin(tentatives);



function begin(canvasid, difficulty, notNew){
	if(tentatives === 0){
		window.alert("Vous n'avez plus de tentatives restantes !");
		return;
	}
	tentatives -= 1;
	insertCoin(tentatives);
	if(notNew === '1'){
		document.getElementById("button_area").removeChild(document.getElementById("lancer_dice"));
	}
	carre = new Square(canvasid, difficulty);
	carre.draw_square();
	carre.drawPlayer();
	playedGames();
}


function Point(canvasid, lettre, x, y){
	this.lettre = lettre;
	this.x = x;
	this.y = y;
}

function Square(canvasid, difficulty){
	this.canvas = document.getElementById(canvasid);	
	
	this.width = this.canvas.width*0.8;
	this.height = this.canvas.height*0.69;
	this.startingPoint = new Point(canvasid, "", this.canvas.width*0.1, this.canvas.height*0.15);
	
	this.a = new Point(canvasid, "A", this.canvas.width*0.09, this.canvas.height*0.12);
	this.b = new Point(canvasid, "B", this.canvas.width*0.88, this.canvas.height*0.12);
	this.c = new Point(canvasid, "C", this.canvas.width*0.88, this.canvas.height*0.96);
	this.d = new Point(canvasid, "D", this.canvas.width*0.09, this.canvas.height*0.96);
	this.sommets = [this.a, this.b, this.d, this.c];
	this.player = this.sommets[0];
	majButton();
	
	this.ctx = this.canvas.getContext("2d");
	this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
	
	
	this.draw_square = function (){
		this.ctx.strokeRect(this.startingPoint.x, this.startingPoint.y, this.width, this.height);
		this.ctx.font = "15px Verdana";
		this.ctx.fillText(this.a.lettre, this.a.x, this.a.y, 11);
		this.ctx.fillText(this.b.lettre, this.b.x, this.b.y, 11);
		this.ctx.fillText(this.c.lettre, this.c.x, this.c.y, 11);
		this.ctx.fillText(this.d.lettre, this.d.x, this.d.y, 11);
	};
	
	this.getPlayer = function(){
		return this.player;
	};
	
	this.drawPlayer = function(){
		this.ctx.fillStyle = "#1ab534";
		this.ctx.fillText(this.getPlayer().lettre, this.getPlayer().x, this.getPlayer().y, 11);
		this.ctx.fillStyle = "#000000";
	};
	
	
}

function majButton(){
	var bouton = document.getElementById("new_game");
	bouton.textContent = "Relancer une nouvelle partie";
	bouton.setAttribute("onclick", "begin('terrain', '0', '1')");
	var boutonLancer = document.createElement("button");
	boutonLancer.setAttribute("onclick", "dice_gen()");
	boutonLancer.setAttribute("id", "lancer_dice");
	boutonLancer.appendChild(document.createTextNode("Lancer le dé"));
	document.getElementById("button_area").appendChild(boutonLancer);
}

function dice_gen(){
        var resultat = randomInt(1, 6);
	switch(resultat%2){
		case 0:
			if((getIndex(carre.sommets, carre.getPlayer())%2 === 0) || (getIndex(carre.sommets, carre.getPlayer()) === 0)){
				carre.player = carre.sommets[getIndex(carre.sommets, carre.getPlayer())+1];
				break;
			} else {
				carre.player = carre.sommets[getIndex(carre.sommets, carre.getPlayer())-1];
				break;
			}
		case 1:
			carre.player = carre.sommets[(getIndex(carre.sommets, carre.getPlayer())+2)%4];
			break;
	}
	carre.ctx.clearRect(0, 0, carre.canvas.width, carre.canvas.height);
	carre.draw_square();
	carre.drawPlayer();
        if(carre.player.lettre === 'C'){
            var boutonLancer = document.getElementById("lancer_dice");
            boutonLancer.textContent = "Bravo !";
            boutonLancer.setAttribute("onclick", "");
            boutonLancer.style.backgroundColor = 'green';
        }
}


function playedGames(id, nombre){
}

function insertCoin(tentatives){
	var essais = document.getElementById("essais");
	essais.innerHTML = tentatives + " parties restantes";
}

function randomInt(min, max){
	return Math.floor(Math.random() * (max - min + 1)) + min;
}

function getIndex(array, value){
	return array.indexOf(value);
}