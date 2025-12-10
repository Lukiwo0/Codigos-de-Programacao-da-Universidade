package com.example.cuidapet.data.petcare

data class PetTip(
    val category: String,
    val title: String,
    val text: String,
    val sourceName: String,
    val sourceUrl: String
)

enum class PetType(
    val displayName: String,
    val comingSoon: Boolean = false
) {
    CACHORRO(displayName = "Cachorro"),
    GATO(displayName = "Gato"),
    HAMSTER(displayName = "Hamster", comingSoon = true)
}

object PetCareData {
    private val dogTips = listOf(
        PetTip(
            category = "alimentacao",
            title = "Alimentação básica",
            text = """
                A alimentação de cães deve ser balanceada, garantindo todos os nutrientes essenciais. 
                Rações comerciais de boa qualidade geralmente suprem todas as necessidades do animal. 
                Evite oferecer alimentos caseiros sem orientação veterinária, pois podem causar deficiências nutricionais. 
                Sempre mantenha água fresca e limpa disponível.
                """.trimIndent(),
            sourceName = "Fonte: Conselho Federal de Medicina Veterinária (CFMV)",
            sourceUrl = "https://www.cfmv.gov.br/"
        ),

        PetTip(
            category = "saude-preventiva",
            title = "Saúde preventiva",
            text = """
                Vacinação, vermifugação e visitas regulares ao veterinário são essenciais. 
                A prevenção de parasitas (pulgas, carrapatos e vermes) evita doenças graves. 
                Além disso, mantenha a higiene bucal e cheque o peso periodicamente.
                """.trimIndent(),
            sourceName = "Fonte: Associação Brasileira de Clínicos Veterinários de Pequenos Animais (Anclivepa)",
            sourceUrl = "https://www.anclivepa.org.br/"
        )
    )


    private val catTips = listOf(
        PetTip(
            category = "alimentacao",
            title = "Alimentação básica",
            text = """
                    Gatos são carnívoros estritos, então sua dieta deve conter proteínas de alta qualidade. 
                    Rações específicas para gatos são formuladas com os nutrientes adequados. 
                    Evite oferecer comida de cachorro ou restos de comida humana.
                """.trimIndent(),
            sourceName = "Fonte: Sociedade Brasileira de Medicina Felina (SBMFel)",
            sourceUrl = "https://www.sbmfel.org.br/"
        ),

        PetTip(
            category = "higiene",
            title = "Higiene e limpeza",
            text = """
                    A limpeza da caixa de areia é essencial para o conforto e a saúde do gato. 
                    Escove os pelos regularmente e mantenha o ambiente tranquilo e enriquecido. 
                    Gatos também precisam de check-ups veterinários periódicos.
                """.trimIndent(),
            sourceName = "Fonte: Conselho Regional de Medicina Veterinária de São Paulo (CRMV-SP)",
            sourceUrl = "https://www.crmvsp.gov.br/"
        )

    )

    fun getTipsForPet(petType: String) : List<PetTip> = when (petType.lowercase()) {
        "cachorro" -> dogTips
        "gato" -> catTips

        else -> emptyList()
    }

    fun getTipDetail(petType: String, category: String): PetTip =
        getTipsForPet(petType).first { it.category == category }

}