<template>
<div id="app">
    <div class="wrapper clearfix">
        <Players :listPlayer="listPlayer" :hasWinner="hasWinner" :isPlaying="isPlaying" />

        <Controll v-on:playNewGame="handleNewGame" :finalScore="finalScore" :hasWinner="hasWinner" :isStart="isStart" :isPlaying="isPlaying" :listPlayer="listPlayer" v-on:rollDice="handleRollDice" v-on:hold="handleHold" />
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
import Players from "./components/Players.vue";
import Controll from "./components/Controll.vue";

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
            hasWinner: false
        }
    },
    components: {
        Players,
        Controll
    },
    methods: {
        handleNewGame(data) {
            this.hasWinner = data.hasWinner;
            this.listPlayer = data.listPlayer;
            this.isStart = !this.isStart;
            alert("Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo, asperiores animi voluptates eius officia autem quasi excepturi, architecto minima ipsam minus, molestias cupiditate sapiente fugit commodi blanditiis amet possimus voluptatem!");
            this.isPlaying = 1;
        },
        handleRollDice(data) {
            this.dice = data.dice;
            this.isPlaying = data.isPlaying;
        },
        handleHold(data) {
            this.listPlayer = data.listPlayer;
            this.isStart = data.isStart;
            this.isPlaying = data.isPlaying;
            this.hasWinner = data.hasWinner;
        }
    },

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
