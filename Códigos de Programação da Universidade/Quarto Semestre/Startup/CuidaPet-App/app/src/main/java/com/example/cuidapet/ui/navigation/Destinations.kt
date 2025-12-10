package com.example.cuidapet.ui.navigation

object Destinations {

    // ONBOARDING
    const val ONBOARDING = "onboarding"
    const val ONBOARDING_INTRO = "onboardingIntro"
    const val ONBOARDING_MISSION = "onboardingMission"
    const val ONBOARDING_FEATURES = "onboardingFeatures"
    const val ONBOARDING_USER_DATA = "onboardingUserData"
    const val ONBOARDING_PET_DATA = "onboardingPetData"
    const val ONBOARDING_END = "onboardingEnd"


    // MAIN
    const val MAIN = "main"
    const val HOME = "home"
    const val USER_PROFILE = "userProfile"
    const val ALL_NOTIFICATIONS = "allNotifications"
    const val PET_CARE_TIPS = "petCareTips"
    const val PET_TYPE_TIPS = "petTypeTips"
    const val PET_TIP_DETAIL = "petTipDetail"


    const val SUPPORT = "support"
    const val CREDITS = "credits"


    // PET
    const val PET_BASE = "pet"
    const val PET_LIST = "$PET_BASE/list"
    const val PET_REGISTER = "$PET_BASE/register"

    // PET DETALHES (sub rota)
    fun petProfileRoute (petId: String) = "$PET_BASE/$petId/profile"
    fun petOverviewRoute (petId: String) = "$PET_BASE/$petId/overview"

    fun petVaccinesRoute (petId: String) = "$PET_BASE/$petId/vaccines"
    fun petVaccineRegisterRoute (petId: String, vaccineId: String) = "$PET_BASE/$petId/vaccines/$vaccineId"
    fun petVaccineDetailRoute (petId: String) = "$PET_BASE/$petId/vaccines/register"

    fun petPetMedicalRecordsRoute (petId: String) = "$PET_BASE/$petId/medical-records"
    fun petPetMedicalRecordRegisterRoute (petId: String, medicalRecordId: String) = "$PET_BASE/$petId/medical-records/$medicalRecordId"
    fun petPetMedicalRecordDetailRoute (petId: String) = "$PET_BASE/$petId/medical-records/register"

    fun petAlertsRoute (petId: String) = "$PET_BASE/$petId/alerts"
    fun petAlertRegisterRoute (petId: String, alertId: String) = "$PET_BASE/$petId/alerts/$alertId"
    fun petAlertDetailRoute (petId: String) = "$PET_BASE/$petId/alerts/register"

    fun petNotesRoute (petId: String) = "$PET_BASE/$petId/notes"
    fun petNoteRegisterRoute (petId: String, noteId: String) = "$PET_BASE/$petId/notes/$noteId"
    fun petNoteDetailRoute (petId: String) = "$PET_BASE/$petId/notes/register"

}
