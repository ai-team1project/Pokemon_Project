window.addEventListener("DOMContentLoaded", function(){
const checkAlls=document.getElementsByClassName("check-all");
for(const el of checkAlls){
el.addEventListener("click",function(){
const {targetClass}=this.dataset;
if(!targetClass){
return;}
const chks=document.getElementsByClassName(targetClass);
for(const chk of chks){
chk.checked=this.checked;
}
});
}
});