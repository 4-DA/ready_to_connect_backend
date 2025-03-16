from rest_framework.permissions import AllowAny
from rest_framework.exceptions import APIException
from allauth.account.admin import EmailAddress
from rest_framework.generics import get_object_or_404
from django.contrib.auth import get_user_model
from allauth.account.models import EmailConfirmationHMAC, EmailConfirmation
from rest_framework import status
from rest_framework.response import Response
from dj_rest_auth.registration.views import VerifyEmailView
from django.utils.translation import gettext_lazy as _
from allauth.account.utils import send_email_confirmation
from rest_framework.views import APIView
from .serializers import NewEmailConfirmation

# Used in the NewEmailConfirmation view
User = get_user_model()


class CustomConfirmEmailView(VerifyEmailView):
    def get(self, *args, **kwargs):
        key = kwargs.get("key")
        confirmation = None

        # Try to get the confirmation object
        try:
            confirmation = EmailConfirmationHMAC.from_key(key)
            if confirmation is None:
                confirmation = EmailConfirmation.objects.get(key=key)
        except EmailConfirmation.DoesNotExist:
            return Response(
                {"message": _("Invalid or expired confirmation key.")},
                status=status.HTTP_400_BAD_REQUEST
            )

        # Confirm the email
        confirmation.confirm(self.request)

        return Response(
            {"message": _("Email successfully confirmed.")},
            status=status.HTTP_200_OK
        )


class NewEmailConfirmation(APIView):
    permission_classes = [AllowAny]
    serializer_class = NewEmailConfirmation

    def post(self, request):
        user = get_object_or_404(User, email=request.data['email'])
        emailAddress = EmailAddress.objects.filter(
            user=user, verified=True).exists()

        if emailAddress:
            return Response({'message': 'This email is already verified'}, status=status.HTTP_400_BAD_REQUEST)
        else:
            try:
                send_email_confirmation(request, user=user)
                return Response({'message': 'Email confirmation sent'}, status=status.HTTP_201_CREATED)
            except APIException:
                return Response({'message': 'This email does not exist, please create a new account'}, status=status.HTTP_403_FORBIDDEN)
