from django.urls import path
from django.urls import path, include, re_path
from dj_rest_auth.views import LoginView, LogoutView
from dj_rest_auth.views import PasswordResetView, PasswordResetConfirmView
from .views import CustomConfirmEmailView, NewEmailConfirmation
from rest_framework_simplejwt.views import (TokenRefreshView)


urlpatterns = [
    path('registration/', include('dj_rest_auth.registration.urls')),
    path('login/', LoginView.as_view()),
    path('logout/', LogoutView.as_view()),
    re_path("confirm-email/(?P<key>[-:\w]+)/$",
            CustomConfirmEmailView.as_view()),
    path('resend-confirmation-email/', NewEmailConfirmation.as_view()),
    path('password-reset/', PasswordResetView.as_view()),
    path('password-reset-confirm/<uidb64>/<token>/',
         PasswordResetConfirmView.as_view(), name='password_reset_confirm'),

    # Retrieve new access token
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
]
