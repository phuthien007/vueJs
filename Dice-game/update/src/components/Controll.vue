<template>
<div class="wrap-control">
    <button class="control btn-new" v-bind:disabled="isStart" v-on:click="playing"><i class="ion-ios-plus-outline"></i>New game</button>
    <button @click="handleRollDice" :disabled="changeStatus" class="control btn-roll "><i class="ion-ios-loop"></i>Roll dice</button>
    <button class="control btn-hold" :disabled="changeStatus" @click="handleHold"><i class="ion-ios-download-outline"></i>Hold</button>

</div>
</template>

<script>
export default {
    name: 'control-play',
    data() {
        return {

        }
    },
    props: {
        listPlayer: Array,
        isPlaying: Number,
        isStart: Boolean,
        hasWinner: Boolean,
        finalScore: Number
    },
    methods: {
        playing() {
            let data = {
                hasWinner: false,
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

            }
            this.$emit("playNewGame", data);

        },
        handleRollDice() {
            // Returns a random integer from 1 to 6:
            do {
                var var1 = Math.floor(Math.random() * 7);
                // Returns a random integer from 1 to 6:
                var var2 = Math.floor(Math.random() * 7);
            } while (var1 * var2 == 0)
            if (var1 == 1 || var2 == 1) {
                this.listPlayer[this.isPlaying - 1].currentScore = 0;
                if (this.isPlaying === 1) {
                    this.isPlaying = 2;
                } else {
                    this.isPlaying = 1;
                }
                alert("lost turn");
            } else {
                this.listPlayer[this.isPlaying - 1].currentScore += (var1 + var2);
                var dice = [];
                switch (var1) {
                    case 1:
                        dice.push({
                            "dice-1": true
                        });
                        break;
                    case 2:
                        dice.push({
                            "dice-2": true
                        });
                        break;
                    case 3:
                        dice.push({
                            "dice-3": true
                        });
                        break;
                    case 4:
                        dice.push({
                            "dice-4": true
                        });
                        break;
                    case 5:
                        dice.push({
                            "dice-5": true
                        });
                        break;
                    case 6:
                        dice.push({
                            "dice-6": true
                        });
                        break;

                }
                switch (var2) {
                    case 1:
                        dice.push({
                            "dice-1": true
                        });
                        break;
                    case 2:
                        dice.push({
                            "dice-2": true
                        });
                        break;
                    case 3:
                        dice.push({
                            "dice-3": true
                        });
                        break;
                    case 4:
                        dice.push({
                            "dice-4": true
                        });
                        break;
                    case 5:
                        dice.push({
                            "dice-5": true
                        });
                        break;
                    case 6:
                        dice.push({
                            "dice-6": true
                        });
                        break;

                }
            }
            let data = {
                dice: dice,
                isPlaying: this.isPlaying
            };
            this.$emit("rollDice", data);

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
            let data = {
                listPlayer: this.listPlayer,
                hasWinner: this.hasWinner,
                isPlaying: this.isPlaying,
                isStart: this.isStart
            };
            this.$emit('hold', data);
        }
    },
    computed: {
        changeStatus() {
            if (this.isStart == false)
                return true;
            else return false;
        }
    }
}
</script>

<style>
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
</style>
