import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  results: any[];

  constructor(private http: HttpClient) {
    this.results = [];
    this.results.push(
      {
        author: 'Pera Peric',
        title: 'Naucni rad 1',
        abstract: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris varius tortor in ipsum egestas, vel ullamcorper quam fringilla. Suspendisse a tincidunt urna. Nulla vitae blandit tellus. Nunc gravida mi felis, sit amet fermentum odio vestibulum sed. Fusce non efficitur nibh, sit amet laoreet mi. Ut consectetur in leo vel facilisis. In egestas ut justo vel congue. Donec posuere orci nec consequat maximus. Fusce ut faucibus mi, eleifend imperdiet odio. Maecenas pharetra nibh sit amet nibh imperdiet ultrices.',
        price: 1200,
        currency: 'RSD',
        issn1: '1234',
        issn2: '5679',
        published: 'Oktobar 2016'
      },
      {
        author: 'Mika Mikic',
        title: 'Naucni rad 2',
        abstract: 'Donec dapibus diam ut condimentum aliquam. Cras mattis lorem ut quam gravida, ut ornare velit dignissim. In condimentum, enim at fringilla tincidunt, erat odio maximus odio, et molestie lacus turpis quis turpis. Ut metus ex, tempus in erat non, congue volutpat nunc. Integer id eros congue, scelerisque libero sed, consequat nisl. Integer congue interdum eros in auctor. Cras sed facilisis felis.',
        price: 500,
        currency: 'RSD',
        issn1: '1234',
        issn2: '5679',
        published: 'Februar 2018'
      },
      {
        author: 'Mika Peric',
        title: 'Naucni rad 3',
        abstract: 'Vivamus lacinia massa a felis maximus, semper maximus nibh laoreet. Donec laoreet sem ac pulvinar sollicitudin. Fusce eget magna leo. Sed imperdiet dui nulla, eget placerat dolor lobortis eu. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec nisl magna, consequat a lorem at, maximus laoreet magna. ',
        price: 3200,
        currency: 'RSD',
        issn1: '9765',
        issn2: '4326',
        published: 'Avgust 2017'
      }
    )

  }

  ngOnInit() {
  }

  generateSession(item:any){
    this.http.post<any>('https://localhost:8080/sessions', {'username': 'Kupac', 'issn': item.issn1 + "" + item.issn2, 'merchandise': item.title, 'price': item.price, 'currency': item.currency})
    .subscribe(
      (data) => {
        window.location.href = "https://localhost:4200/#/choose-payment/" + data.token.substring(6, data.token.length);
      }
    )
  }

}
