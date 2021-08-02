<template>
<div id="app">
    <div class="wrapper clearfix">
        <div v-for="player in listPlayer" :key="player.id" class="player-panel "  :class="{active:isPlaying === player.id, winner:hasWinner}">
            <div class="player-name ">{{player.name}}</div>
            <div class="player-score">{{player.score}}</div>
            <div class="player-current-box">
                <div class="player-current-label">Current</div>
                <div class="player-current-score">{{player.currentScore}}</div>
            </div>
        </div>
        <button class="control btn-new" v-bind:disabled="isStart" v-on:click="playing"><i class="ion-ios-plus-outline"></i>New game</button>
        <button @click="handleRollDice" :disabled="changeStatus" class="control btn-roll "><i class="ion-ios-loop"></i>Roll dice</button>
        <button class="control btn-hold" :disabled="changeStatus" @click="handleHold"><i class="ion-ios-download-outline"></i>Hold</button>

        <input type="number" v-model="finalScore" placeholder="Final score" class="final-score" :disabled="isStart">

        <div id="dice-1" class="dice">
            <div class="spinner " :class="dice[0]">
                <div class="face1">1</div>
                <div class="face2">2</div>
                <div class="face3">3</div>
                <div class="face4">4</div>
                <div class="face5">5</div>
                <div class="face6">6</div>
            </div>
        </div>
        <div id="dice-2" class="dice">
            <div class="spinner " :class="dice[1]">
                <div class="face1">1</div>
                <div class="face2">2</div>
                <div class="face3">3</div>
                <div class="face4">4</div>
                <div class="face5">5</div>
                <div class="face6">6</div>
            </div>
        </div>
    </div>

</div>
</template>

<script>
export default {
    name: 'App',
    data() {
        return {
            listPlayer: [{
                    id: 1,
                    name: 'Player 1',
                    score: 0,
                    currentScore: 0
                },
                {
                    id: 2,
                    name: 'Player 2',
                    score: 0,
                    currentScore: 0
                }
            ],
            isPlaying: 0,
            finalScore: 0,
            isStart: false,
            dice: [

            ],
            hasWinner:false
        }
    },
    components: {

    },
    methods: {
        playing() {
            this.isStart = !this.isStart;
            alert("Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo, asperiores animi voluptates eius officia autem quasi excepturi, architecto minima ipsam minus, molestias cupiditate sapiente fugit commodi blanditiis amet possimus voluptatem!");
            this.isPlaying = 1;
        },
        handleRollDice() {
            // Returns a random integer from 1 to 6:
            do {
                var var1 = Math.floor(Math.random() * 7);
                // Returns a random integer from 1 to 6:
                var var2 = Math.floor(Math.random() * 7);
            } while (var1 * var2 == 0)
            this.dice = [];
            if (var1 == 1 || var2 == 1) {
                this.listPlayer[this.isPlaying - 1].currentScore = 0;
                if (this.isPlaying === 1) {
                    this.isPlaying = 2;
                } else {
                    this.isPlaying = 1;
                }
            } else {
                this.listPlayer[this.isPlaying - 1].currentScore += (var1 + var2);

                switch (var1) {
                    case 1:
                        this.dice.push({
                            "dice-1": true
                        });
                        break;
                    case 2:
                        this.dice.push({
                            "dice-2": true
                        });
                        break;
                    case 3:
                        this.dice.push({
                            "dice-3": true
                        });
                        break;
                    case 4:
                        this.dice.push({
                            "dice-4": true
                        });
                        break;
                    case 5:
                        this.dice.push({
                            "dice-5": true
                        });
                        break;
                    case 6:
                        this.dice.push({
                            "dice-6": true
                        });
                        break;

                }
                switch (var2) {
                    case 1:
                        this.dice.push({
                            "dice-1": true
                        });
                        break;
                    case 2:
                        this.dice.push({
                            "dice-2": true
                        });
                        break;
                    case 3:
                        this.dice.push({
                            "dice-3": true
                        });
                        break;
                    case 4:
                        this.dice.push({
                            "dice-4": true
                        });
                        break;
                    case 5:
                        this.dice.push({
                            "dice-5": true
                        });
                        break;
                    case 6:
                        this.dice.push({
                            "dice-6": true
                        });
                        break;

                }
            }

        },
        handleHold() {
            this.listPlayer[this.isPlaying - 1].score += this.listPlayer[this.isPlaying - 1].currentScore;

            if (this.listPlayer[this.isPlaying - 1].score >= this.finalScore) {
                this.listPlayer[this.isPlaying - 1].name = "Winner";
                this.hasWinner = true;
                this.isStart = false;
            } else {
                this.listPlayer[this.isPlaying - 1].currentScore = 0;
                if (this.isPlaying === 1) {
                    this.isPlaying = 2;
                } else {
                    this.isPlaying = 1;
                }
            }

        }
    },
    computed:{
        changeStatus(){
            if( this.isStart == false)
                return true;
            else return false;
        }
    }
}
</script>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;

}

.clearfix::after {
    content: "";
    display: table;
    clear: both;
}

body {
    background-image: linear-gradient(rgba(62, 20, 20, 0.4), rgba(62, 20, 20, 0.4)),
        url('/assets/back.jpg');
    background-size: cover;
    background-position: center;
    font-family: Lato;
    font-weight: 300;
    position: relative;
    height: 100vh;
    color: #555;
}

.wrapper {
    width: 1000px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    box-shadow: 0px 10px 50px rgba(0, 0, 0, 0.3);
    overflow: hidden;
}

/**********************************************
*** PLAYERS
**********************************************/
.player-panel {
    width: 50%;
    float: left;
    height: 600px;
    padding: 100px;
    transition: all .3s ease;
    background-color: #fff;
}

.player-name {
    font-size: 40px;
    text-align: center;
    text-transform: uppercase;
    letter-spacing: 2px;
    font-weight: 100;
    margin-top: 20px;
    margin-bottom: 10px;
    position: relative;
}

.player-score {
    text-align: center;
    font-size: 80px;
    font-weight: 100;
    color: #42b983;
    margin-bottom: 130px;
}

.active {
    background-color: #f7f7f7;
}

.active .player-name {
    font-weight: 300;
}

.active .player-name::after {
    content: "\2022";
    font-size: 47px;
    position: absolute;
    color: #42b983;
    top: -7px;
    right: 10px;

}

.player-current-box {
    background-color: #42b983;
    color: #fff;
    width: 40%;
    margin: 0 auto;
    padding: 12px;
    text-align: center;
}

.player-current-label {
    text-transform: uppercase;
    margin-bottom: 10px;
    font-size: 12px;
    color: #222;
}

.player-current-score {
    font-size: 30px;
}

.winner {
    background-color: #f7f7f7;
}

.winner .player-name {
    font-weight: 300;
    color: #42b983;
}

/**********************************************
*** Control
**********************************************/
.control {
    position: absolute;
    width: 200px;
    left: 50%;
    transform: translateX(-50%);
    color: #555;
    background: none;
    border: none;
    font-family: Lato;
    font-size: 20px;
    text-transform: uppercase;
    cursor: pointer;
    font-weight: 300;
    transition: background-color 0.3s, color 0.3s;
}

.control.disable {
    pointer-events: none;
}

.control:hover {
    font-weight: 600;
}

.control:hover i {
    margin-right: 20px;
}

.control:focus {
    outline: none;
}

.control i {
    color: #42b983;
    display: inline-block;
    margin-right: 15px;
    font-size: 32px;
    line-height: 1;
    vertical-align: text-top;
    margin-top: -4px;
    transition: margin 0.3s;
}

.btn-new {
    top: 45px;
}

.btn-roll {
    top: 403px;
}

.btn-hold {
    top: 467px;
}

.final-score {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    top: 520px;
    color: #555;
    font-size: 18px;
    font-family: 'Lato';
    text-align: center;
    padding: 10px;
    width: 160px;
    text-transform: uppercase;
}

.final-score:focus {
    outline: none;
}

/* Dice */

#dice-1 {
    top: 120px;
}

#dice-2 {
    top: 250px;
}

.dice {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    width: 120px;
    height: 120px;
    box-shadow: 0px 10px 60px rgba(0, 0, 0, 0.10);
}

.spinner div {
    position: absolute;
    width: 120px;
    height: 120px;
    border: 1px solid #ccc;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
    text-align: center;
    line-height: 120px;
    font-size: 100px;
    color: #42b983;
    font-size: 0;
    transition: all .3s ease;
    opacity: 1;
}

.spinner .face1 {
    -webkit-transform: translateZ(60px);
    -ms-transform: translateZ(60px);
    transform: translateZ(60px);
    background-image: url("./assets/dice-1.png");
    background-position: center;
    background-size: cover;
}

.spinner .face2 {
    -webkit-transform: rotateY(90deg) translateZ(60px);
    -ms-transform: rotateY(90deg) translateZ(60px);
    transform: rotateY(90deg) translateZ(60px);
    background-image: url("./assets/dice-2.png");
    background-position: center;
    background-size: cover;
}

.spinner .face3 {
    -webkit-transform: rotateY(90deg) rotateX(90deg) translateZ(60px);
    -ms-transform: rotateY(90deg) rotateX(90deg) translateZ(60px);
    transform: rotateY(90deg) rotateX(90deg) translateZ(60px);
    background-image: url("./assets/dice-3.png");
    background-position: center;
    background-size: cover;
}

.spinner .face4 {
    -webkit-transform: rotateY(180deg) rotateZ(90deg) translateZ(60px);
    -ms-transform: rotateY(180deg) rotateZ(90deg) translateZ(60px);
    transform: rotateY(180deg) rotateZ(90deg) translateZ(60px);
    background-image: url("./assets/dice-4.png");
    background-position: center;
    background-size: cover;
}

.spinner .face5 {
    -webkit-transform: rotateY(-90deg) rotateZ(90deg) translateZ(60px);
    -ms-transform: rotateY(-90deg) rotateZ(90deg) translateZ(60px);
    transform: rotateY(-90deg) rotateZ(90deg) translateZ(60px);
    background-image: url("./assets/dice-5.png");
    background-position: center;
    background-size: cover;
}

.spinner .face6 {
    -webkit-transform: rotateX(-90deg) translateZ(60px);
    -ms-transform: rotateX(-90deg) translateZ(60px);
    transform: rotateX(-90deg) translateZ(60px);
    background-image: url("./assets/dice-6.png");
    background-position: center;
    background-size: cover;
}

.spinner {
    -webkit-transform-style: preserve-3d;
    -ms-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-transform-origin: 60px 60px 0;
    -ms-transform-origin: 60px 60px 0;
    transform-origin: 60px 60px 0;
    -webkit-transition: all .9s ease;
    -o-transition: all .9s ease;
    transition: all .9s ease;
    -webkit-transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
    -ms-transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
    -o-transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
    transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
}

.spinner.dice-1 {
    -webkit-transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
    -ms-transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
    -o-transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
    transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
}

.spinner.dice-2 {
    -webkit-transform: rotateX(0deg) rotateY(-90deg) rotateZ(0deg);
    -ms-transform: rotateX(0deg) rotateY(-90deg) rotateZ(0deg);
    -o-transform: rotateX(0deg) rotateY(-90deg) rotateZ(0deg);
    transform: rotateX(0deg) rotateY(-90deg) rotateZ(0deg);
}

.spinner.dice-3 {
    -webkit-transform: rotateX(0deg) rotateY(-90deg) rotateZ(90deg);
    -ms-transform: rotateX(0deg) rotateY(-90deg) rotateZ(90deg);
    -o-transform: rotateX(0deg) rotateY(-90deg) rotateZ(90deg);
    transform: rotateX(0deg) rotateY(-90deg) rotateZ(90deg);
}

.spinner.dice-4 {
    -webkit-transform: rotateX(0deg) rotateY(180deg) rotateZ(90deg);
    -ms-transform: rotateX(0deg) rotateY(180deg) rotateZ(90deg);
    -o-transform: rotateX(0deg) rotateY(180deg) rotateZ(90deg);
    transform: rotateX(0deg) rotateY(180deg) rotateZ(90deg);
}

.spinner.dice-5 {
    -webkit-transform: rotateX(0deg) rotateY(90deg) rotateZ(0deg);
    -ms-transform: rotateX(0deg) rotateY(90deg) rotateZ(0deg);
    -o-transform: rotateX(0deg) rotateY(90deg) rotateZ(0deg);
    transform: rotateX(0deg) rotateY(90deg) rotateZ(0deg);
}

.spinner.dice-6 {
    -webkit-transform: rotateX(90deg) rotateY(90deg) rotateZ(0deg);
    -ms-transform: rotateX(90deg) rotateY(90deg) rotateZ(0deg);
    -o-transform: rotateX(90deg) rotateY(90deg) rotateZ(0deg);
    transform: rotateX(90deg) rotateY(90deg) rotateZ(0deg);
}
</style>
