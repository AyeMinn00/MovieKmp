import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel : ViewModel
    
    let gridLayoutItems = [GridItem(.flexible()),GridItem(.flexible()),GridItem(.flexible())]
    
    var body: some View {
        ScrollView{
            LazyVGrid(
                columns: gridLayoutItems,
                spacing: 2,
                content: {
                    ForEach(viewModel.movies, id : \.self){ mv in
                    MovieCard(movie : mv)
                }
            })
            .padding(.horizontal, 4)
        }
        .onAppear(perform: {
            self.viewModel.startObserving()
        })
    }
}

struct MovieCard : View {
    
    let movie : Movie
    
    var body: some View{
        ZStack(alignment: .bottomLeading) {
            AsyncImage(
                url: URL(string: "https://image.tmdb.org/t/p/w500\(movie.posterPath ?? "")")
            ){ asyPhase in
                if let image = asyPhase.image{
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                }
            }
            Text(movie.title)
                .font(.caption)
                .fontWeight(.bold)
                .foregroundColor(.white)
                .padding(8)
        }
        .cornerRadius(8)
    }
    
}

struct MovieCard_Previews: PreviewProvider{
    static var previews : some View{
        MovieCard(movie : Movie(id: 1, title: "Cast Away", posterPath: "https://media.themoviedb.org/t/p/w440_and_h660_face/qhb1qOilapbapxWQn9jtRCMwXJF.jpg", originTitle: "Origin Title", voteCount: 22, isVoted: false))
            .previewLayout(.fixed(width: 300, height: 300))
    }
}

extension ContentView{
    
    @MainActor
    class ViewModel : ObservableObject{
        
        @Published var movies : Array<Movie> = []
        let apiService = DefaultTmdbRemoteDataSource()
        
        func startObserving(){
            apiService.getMovies { [weak self] response, error in
                DispatchQueue.main.async {
                    self?.movies.append(contentsOf : response?.results ?? [])
                }
            }
        }
        
    }
    
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//        ContentView(viewModel: ContentView.ViewModel())
//	}
//}
