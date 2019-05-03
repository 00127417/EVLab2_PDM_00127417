package com.dondyprax.corto2labo

class MainListFragment: Fragment(){

    var listenerTool :  SearchNewPokemonListener? = null
    private lateinit var pokemons: ArrayList<Pokemon>
    private lateinit var pokemonsAdapter: MyPokeAdapter

    companion object {
        fun newInstance(dataset : ArrayList<Pokemon>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.pokemons = dataset
            return newFragment
        }
    }


    interface SearchNewPokemonListener{
        fun searchPokemon(pokeId: String)

        fun managePortraitItemClick(pokemon: Pokemon)

        fun manageLandscapeItemClick(pokemon: Pokemon)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.pokemon_list_fragment, container, false)

        if(savedInstanceState != null) pokemons = savedInstanceState.getParcelableArrayList<Pokemon>(AppConstants.MAIN_LIST_KEY)!!

        initRecyclerView(resources.configuration.orientation, view)
        initSearchButton(view)

        return view
    }

    fun initRecyclerView(orientation:Int, container:View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            pokemonsAdapter = PokemonAdapter(pokemons, {pokemon:Pokemon->listenerTool?.managePortraitItemClick(pokemon)})
            container.rv_pokemon_list.adapter = pokemonsAdapter as PokemonAdapter
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            pokemonsAdapter = PokemonSimpleListAdapter (pokemons, { pokemon:Pokemon->listenerTool?.manageLandscapeItemClick(pokemon)})
            container.rv_pokemon_list.adapter = pokemonsAdapter as PokemonSimpleListAdapter
        }

        container.rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun initSearchButton(container:View) = container.searchbarbutton.setOnClickListener {
        listenerTool?.searchPokemon(searchbar.text.toString())
    }




    fun updatePokemonAdapter(pokemonList: ArrayList<Pokemon>){ pokemonsAdapter.changeDataSet(pokemonList) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SearchNewPokemonListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, pokemons)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

}
---------------------------------------------------------------------------------
int ViewId = v.getId();
int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9};
switch (ViewId) {
    case R.id.image1:
    image1.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image2:
    image2.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image3:
    image3.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image4:
    image4.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image5:
    image5.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image6:
    image6.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image7:
    image7.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image8:
    image8.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;
    case R.id.image9:
    image9.setImageResource(images[(int) Math.floor(Math.random()*9)]);
    break;

    ----------------------------------------------------------------------------------------

    lass PokemonViewer : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.viewer_element_pokemon)

            val uri: String = this.intent.extras.getString("CLAVIER")
            //setSupportActionBar(toolbarviewer)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

            FetchPokemonTask().execute(uri)
        }

        fun init(pokemon: Pokemon) {
            Picasso.with(this)
                    .load(pokemon.sprite)
                    .resize((this.resources.displayMetrics.widthPixels / this.resources.displayMetrics.density).toInt(), 256)
                    .centerCrop()
                    .error(R.drawable.ic_pokemon_go)
                    .into(app_bar_image_viewer)
            collapsingtoolbarviewer.title = pokemon.name
            weight.text = pokemon.weight
            height.text = pokemon.height
            fstType.text = pokemon.fsttype
            sndType.text = pokemon.sndtype
        }

        override fun onOptionsItemSelected( item: MenuItem?): Boolean {
            return when (item!!.itemId) {
                android.R.id.home -> {
                    onBackPressed();true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }