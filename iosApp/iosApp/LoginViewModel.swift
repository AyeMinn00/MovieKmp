//
//  LoginViewModel.swift
//  iosApp
//
//  Created by AyeMin00 on 11/2/2567 BE.
//  Copyright © 2567 BE orgName. All rights reserved.
//

import Foundation
import shared

@MainActor
class LoginViewModel : ObservableObject{
    
    @Published var loading = false
    @Published var email = ""
    @Published var emailError: String? = ""
    @Published var password = ""
    @Published var passwordError : String?  = ""
    
    func login(){
        print("login")
        let emailValidation = ExtenstionsKt.isValidatedEmail(email: email)
        let pwdValidation = ExtenstionsKt.isStrongPassword(pwd: password)
        if(!emailValidation.successful){
            emailError = emailValidation.errorMessage
        }
        if(!pwdValidation.successful){
            passwordError = pwdValidation.errorMessage
        }
        if(!emailValidation.successful || !pwdValidation.successful){
            return 
        }
        loading = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 5) { [weak self] in
            self?.loading = false
        }
        print("login done")
    }
    
    func updateEmail(email : String){
        print("update email \(email)")
        self.email = email
    }
    
    func updatePassword(pwd : String){
        self.password = pwd 
    }
    
}
