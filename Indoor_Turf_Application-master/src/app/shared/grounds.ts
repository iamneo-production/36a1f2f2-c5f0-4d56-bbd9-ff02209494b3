export interface Ground {
    GroundID: string;
    GroundName: string;
    GroundImageURL: string;
    GroundAddress: string;
    GroundDescription: string;
    groundAvailabeStatus: string;
    price: string;
  }
  
  export const grounds = [
    {
      GroundID: 1,
      GroundName: 'Area 777',
      GroundImageURL:'../../../assets/ground.jpg' ,
      GroundAddress:'Chennai' ,
      GroundDescription:'A large area with one of the best games',
      groundAvailabeStatus:'4pm to 11pm',
      price: '1500'
    },
    {
      GroundID: 2,
      GroundName: '7th Yard',
      GroundImageURL:'../../../assets/ground1.jpg' ,
      GroundAddress:'Coimbatore' ,
      GroundDescription:'A great place with one of the best ground',
      groundAvailabeStatus:'4pm to 11pm',
      price: '1500'
    },
    {
      GroundID: 3,
      GroundName: 'Yonex stadium',
      GroundImageURL:'../../../assets/ground2.jpg' ,
      GroundAddress:'Bangalore' ,
      GroundDescription:'More Space More Games',
      groundAvailabeStatus:'4pm to 11pm',
      price:'1500'
    },
    {
      GroundID: 4,
      GroundName: 'Timeless Space Yard',
      GroundImageURL:'../../../assets/ground.jpg' ,
      GroundAddress:'Kerala' ,
      GroundDescription:'Enjoy your time here with your family and friends',
      groundAvailabeStatus:'4pm to 11pm',
      price: '1500'
    }
  ];
  