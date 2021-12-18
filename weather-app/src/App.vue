<template>
  <div id="app">
    <main>
      <div class="search-box">
        <input v-model="query" type="text" placeholder="Search..." class="search-bar">
        {{query}}
      </div>
      <div class="weather-wrap">
        <div class="location-box">
          <div class="location">
            Ha Noi, VN
          </div>
          <div class="date">Friday, August 6, 2021</div>
        </div>
        <div class="weather-box">
          <div class="temp">39<sup><small>o</small></sup>C</div>
          <div class="weather">Hot</div>
        </div>
      </div>
    </main>
     
  </div>
</template>

<script>

export default {
  name: 'App',
  data(){
    return {
      query:'',
      api_key:'1acfbe301786db7b147752ebf91ec509'
    }
  },
  components: {
  
  }
}
</script>

<style>

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body{
  font-family: 'Courier New', Courier, monospace;
}

#app{
  background-image: url('./assets/images/Cloudy Weather.jpg');
  background-size: cover;
  background-position: 0.4s;
  max-width: 100%;
}

main{
  min-height: 100vh;
  padding: 25px;
  background-image: linear-gradient(to bottom, rgba(0,0,0,0.25), rgba(0,0,0,0.75));
}

.search-box{
  width: 50%;
  margin-bottom: 30px;
  margin-left: 25%;
}

.search-box .search-bar{
  width: 100%;
  display: block;
  padding: 15px ;
  color: #313131;
  font-size: 20px;
  outline: none;
  background: none;
  background-color: rgba(255,255,255,0.5);
  border-radius: 0 16px 0 16px;
  transition: 0.4s;
}

.location-box .location{
  color: #FFF;
  font-size: 32px;
  font-weight: 500;
  text-align: center;
  text-shadow: 1px 3px rgba(0,0,0,0.25);
}

.location-box .date{
  color: #FFF;
  font-size: 20px;
  font-weight: 300;
  font-style: italic;
  text-align: center;
}

.weather-box{
  text-align: center;
}

.weather-box .temp{
  width: 50%;
  display: inline-block;
  padding: 10px 25px;
  color: #FFF;
  font-size: 100px;
  font-weight: 900;

  text-shadow: 3px 6px rgba(0,0,0,0.25);
  background-color: rgba(255,255,255, 0.25);
  border-radius: 16px;
  margin: 30px 0px;
  box-shadow: 3px 6px rgba(0,0,0,0.25) ;
}

.weather-box .weather{
  color: #FFF;
  font-size: 48px;
  font-weight: 700;
  font-style: italic;
  text-shadow: 3px 6px rgba(0,0,0,0.25);
}

</style>
