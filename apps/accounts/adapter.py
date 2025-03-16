from allauth.account.adapter import DefaultAccountAdapter
from django.conf import settings


class CustomAccountAdapter(DefaultAccountAdapter):
    def send_mail(self, template_prefix, email, context):

        if template_prefix == "account/email/email_confirmation_signup":  # Registration email
            # # Modify the activation link
            activate_url = f"{
                settings.BASE_ACCOUNTS_URL}/confirm-email/{context['key']}/"
            context["activate_url"] = activate_url
        elif template_prefix == "account/email/password_reset_key":  # Password reset email
            # # Modify the reset link
            reset_url = f"{
                settings.BASE_ACCOUNTS_URL}/password-reset-confirm/{context['uid']}/{context['token']}/"
            context["password_reset_url"] = reset_url

        super().send_mail(template_prefix, email, context)
