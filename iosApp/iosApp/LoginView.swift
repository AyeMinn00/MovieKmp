//
//  LoginView.swift
//  iosApp
//
//  Created by AyeMin00 on 11/2/2567 BE.
//  Copyright © 2567 BE orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View{
    
    @ObservedObject private(set) var viewModel : LoginViewModel
    
    var body: some View{
        VStack( alignment: .center , spacing: 4) {
            TextField(
                "Email",
                text: $viewModel.email)
            .onChange(of: viewModel.email) { oldValue, newValue in
                viewModel.updateEmail(email: newValue)
            }
            if let emailError = viewModel.emailError{
                Text(emailError)
            }else{
                EmptyView()
            }
            SecureField("Password", text: $viewModel.password)
            if(viewModel.passwordError != nil){
                Text(viewModel.passwordError ?? "")
            }else{
                EmptyView()
            }
            
            Button("Login"){
                viewModel.login()
            }
            .padding([.leading, .trailing], 64)
            .padding([.top, .bottom], 8)
            .background(Color(red: 0, green: 0, blue: 0.9))
            .foregroundColor(.white)
            .clipShape(Capsule())
        }
    }
    
}

struct LoginView_Preview : PreviewProvider{
    static var previews: some View{
        LoginView(viewModel: LoginViewModel())
    }
}
