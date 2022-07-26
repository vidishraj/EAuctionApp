
import  './App.css';
import Allowance from './ProductInfo'
import React, {useState}  from "react";

class Bids extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      ProductName: "",
      Description: "",
      LongDescription: "",
      Category: "",
      StartingPrice: "",
      BidEnd: "",
      Bids: []
    };
  }
  compare( a, b ) {
    if ( a.BidAmount < b.BidAmount ){
      return -1;
    }
    if ( a.BidAmount > b.BidAmount ){
      return 1;
    }
    return 0;
  }
  reset(){
    this.setState( {
      ProductName: "",
      Description: "",
      LongDescription: "",
      Category: "",
      StartingPrice: "",
      BidEnd: "",
      Bids: []
    });
  }
  onClick = () =>{
    fetch("http://localhost:8080/e-auction/api/v1/seller/show-bids/"+document.getElementById("ip").value)
          .then(data => {
            
            if(data.ok){
              return data.json();
            }
            else{
              this.reset();
              alert("Product does not exist");
            }
          })
          .then(data => {
            data.Bids.sort(this.compare);
            this.setState({ProductName: data.ProductName,
              Description: data.Description,
              LongDescription: data.LongDescription,
              Category: data.Category,
              StartingPrice: data.StartingPrice,
              BidEnd: data.BidEnd,
              Bids: data.Bids
            });
          })
          .catch(err => {
            console.log(err);
          })
  }
  render() {
    return( 
    <div>
    <img id="logo" src="https://w7.pngwing.com/pngs/1000/710/png-transparent-bidding-forward-auction-google-play-unique-bid-auction-auction-text-logo-internet-thumbnail.png">
      </img>
    <p id="searchtext"> Enter the Product ID</p>
    <div id="parent">
      <input id ="ip" type="text"></input>
      <button id="bt" onClick={this.onClick}> Fetch</button>
    </div>
      <div id="linecontainer">
            <p id="nameheader"> Product Name</p>
            <p id="nameheader"> {this.state.ProductName}</p>
        </div>

        <div id="linecontainer">
            <p id="nameheader">Short Description</p>
            <p id="nameheader"> {this.state.Description}</p>
        </div>

        <div id="linecontainer">
            <p id="nameheader"> Detailed Description</p>
            <p id="nameheader"> {this.state.LongDescription}</p>
        </div>

        <div id="linecontainer">
            <p id="nameheader"> Category</p>
            <p id="nameheader"> {this.state.Category}</p>
        </div>

        <div id="linecontainer">
            <p id="nameheader">Starting Price</p>
            <p id="nameheader"> {this.state.StartingPrice}</p>
        </div>

        <div id="linecontainer">
            <p id="nameheader">Bid End Date</p>
            <p id="nameheader"> {this.state.BidEnd}</p>
        </div>
        <p id="tablehead">Bids</p>
        <table>
            <tr>
              <th>Bid Amount</th>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
            </tr>
        {this.state.Bids?.map(employee => {
        return (
          <tr>
            <th>{employee.BidAmount}</th>
            <th>{employee.FirstName}</th>
            <th>{employee.Email}</th>
            <th>{employee.Phone}</th>
          </tr>
            );
          }
        )}
        </table>
        <div>
          
        </div>
      
      </div>
  )
  }
}

export default Bids;