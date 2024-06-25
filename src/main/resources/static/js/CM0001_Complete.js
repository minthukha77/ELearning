$(document).ready(function () {
    
   
  
    $(".back").click(function (event) {
      //TODO prevent external link
  
      event.preventDefault();
  
      if(isInternal() && history.length > 0){
        history.back(1);
      }
      
    });

    $(".back2").click(function (event) {
        //TODO prevent external link
    
        event.preventDefault();
    
        if(isInternal() && history.length > 0){
          history.back(2);
        }
        
      });

  });
  
  
  function isInternal(){
    /* Get Previous Page */
    const prevPage = document.referrer;
    /* Get current Page Host */
    const regExp = new RegExp(location.host);
    return regExp.test(prevPage);
    
  }
  
  
  
  
  /* 
   */