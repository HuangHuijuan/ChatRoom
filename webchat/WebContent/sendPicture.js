var pic=new Array(100);//
var count=0;
function change(obj){
//	for( var i = 0 ; i < obj.files.length ; i++ ){
	    var file = obj.files[0];   
        if(!/image\/\w+/.test(file.type)){   
            alert("请确保文件为图像类型!"); 
            return false; 
        } 
        var reader = new FileReader(); 
        reader.readAsDataURL(file); 
        reader.onload = function(e){ 
        var ds=document.getElementById("DataToSend");
        pic[count]=this.result.replace(/(.*?),/,"\#pic\#\*pic\*")+"\*end\*\#pic\#";
        ds.value = ds.value+"\#picture"+count+"\#";
        count++;
        //"\#pic\#<!--\*pic\*"+this.result+"\*end\*\#pic\#-->"; 
        } 
	   var nf = obj.cloneNode(true);
       nf.value=''; 
       obj.parentNode.replaceChild(nf, obj);
	
};

window.onload = function(){ 
    var pic = document.getElementById("file"); 
    if ( typeof(FileReader) === 'undefined' ){ 
    	    Log("抱歉，你的浏览器不支持 FileReader，不能发送图片。", "ERROR");
            pic.setAttribute( 'disabled','disabled' ); 
    } 
}; 

