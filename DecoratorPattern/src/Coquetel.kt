interface Coquetel {

    fun misturarBebida()
}

class CoquetelBase : Coquetel {

    override fun misturarBebida() {
        print("Bebida Básica.")
    }

}

open class CoquetelDecorator(private val bebida: Coquetel) : Coquetel {

    override fun misturarBebida() {
        this.bebida.misturarBebida()
    }

}

class Cachaca(c: Coquetel) : CoquetelDecorator(c) {

    override fun misturarBebida() {
        super.misturarBebida()
        print(" Adicionando Cachaça.")
    }
    
}

class Licor(c: Coquetel) : CoquetelDecorator(c) {

    override fun misturarBebida() {
        super.misturarBebida()
        print(" Adicionando Licor.")
    }

}

class Uva(c: Coquetel) : CoquetelDecorator(c){

    override fun misturarBebida() {
        super.misturarBebida()
        print(" Adicionando Uva.")
    }

}


fun main(args: Array<String>) {

    val cachaca = Cachaca(CoquetelBase())
    cachaca.misturarBebida()
    println("\n*****")

    val cachacaLicor = Cachaca(Licor(CoquetelBase()))
    cachacaLicor.misturarBebida()
    println("\n*****")

    val cachacaLicorUva = Cachaca(Licor(Uva(CoquetelBase())))
    cachacaLicorUva.misturarBebida()
}