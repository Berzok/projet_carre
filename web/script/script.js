

var carre;

function begin(canvasid, difficulty, notNew){
	if(notNew === 1){
		document.getElementById("jeu").removeChild(document.getElementById("lancer_dice"));
	}
	carre = new Square(canvasid, difficulty);
	carre.draw_square();
}


function Point(canvasid, lettre, x, y){
	var canvas = document.getElementById(canvasid);
	this.lettre = lettre;
	this.x = x;
	this.y = y;
}

function Square(canvasid, difficulty){
	this.canvas = document.getElementById(canvasid);
	this.ctx = this.canvas.getContext("2d");
	this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
	this.a = new Point(canvasid, "A", this.canvas.width*0.1, this.canvas.height*0.12);
	this.b = new Point(canvasid, "B", this.canvas.width*0.88, this.canvas.height*0.12);
	this.c = new Point(canvasid, "C", this.canvas.width*0.88, this.canvas.height*0.96);
	this.d = new Point(canvasid, "D", this.canvas.width*0.1, this.canvas.height*0.96);
	this.sommets = [this.a, this.b, this.c, this.d];
	this.player = this.sommets[0];
	majButton();
	
	
	this.draw_square = function (){
		this.ctx.strokeRect(this.a.x, this.a.y+this.canvas.height*0.03, this.canvas.width*0.8, (this.canvas.height*0.75)-this.canvas.height*0.05);
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
		this.ctx.strokeStyle = "FF0000";
		this.ctx.strokeRect(this.getPlayer().x, this.getPlayer().y+this.canvas.height*0.03, this.canvas.width*0.02, this.canvas.height*0.02);
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
	document.getElementById("jeu").appendChild(boutonLancer);
}

function dice_gen(){
	switch(Math.random()){
		case 0:
			if(carre.sommets.indexOf(carre.getPlayer())%2 === 0){
				carre.player = carre.sommets.indexOf(carre.getPlayer())+1;
			} else {
				carre.player = carre.sommets.indexOf(carre.getPlayer())-1;
			}
		case 1:
			carre.player = (carre.sommets.indexOf(carre.getPlayer())+2)%4;
	}
	carre.drawPlayer();
}