



let liveBtn = document.querySelector('#live');
let liveCon = document.querySelector('.live-session');
let recordedBtn = document.querySelector('#recorded');
let recordedCon = document.querySelector('.record-class');

let remove = (a,b,c,d)=>{
  a.classList.remove('active');
  b.classList.add('active');
  c.classList.add('active');
  d.classList.remove('active')
}
liveBtn.addEventListener('click',()=>{
  if(liveBtn.classList.contains('active')){
    
  }else{
    remove(recordedBtn,liveBtn,liveCon,recordedCon)
  }
})
recordedBtn.addEventListener('click',()=>{
  if(recordedBtn.classList.contains('active')){
    
  }else{
    remove(liveBtn,recordedBtn,recordedCon,liveCon)
  }
})